package br.com.codenation;

import br.com.codenation.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.exceptions.TimeNaoEncontradoException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;


public class DesafioMeuTimeApplication implements MeuTimeInterface {
	private final List<Time> teamsList = new ArrayList<>();
	private final List<Jogador> playersList = new ArrayList<>();

	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
		// Caso o identificador já exista, retornar br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException
		Time t = new Time(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario);
		Optional<Time> teamSelected = teamsList.stream().filter((team) -> team.getId().equals(id)).findAny();
		if (teamSelected.isPresent()) {
			throw new IdentificadorUtilizadoException();
		} else {
			teamsList.add(t);
		}
	}

	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
		// Caso o identificador já exista, retornar br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException
		// Caso o time informado não exista, retornar br.com.codenation.desafio.exceptions.TimeNaoEncontradoException
		Optional<Time> teamSelected = teamsList.stream().filter((team) -> team.getId().equals(idTime)).findAny();
		Optional<Jogador> playerSelected = playersList.stream().filter((player) -> player.getId().equals(id)).findAny();
		if (!teamSelected.isPresent()) {
			throw new TimeNaoEncontradoException();
		} else if (playerSelected.isPresent()) {
			throw new IdentificadorUtilizadoException();
		} else {
			Jogador p = new Jogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario);
			playersList.add(p);
		}
	}

	public void definirCapitao(Long idJogador) {
		// Caso o jogador informado não exista, retornar br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException
		Optional<Jogador> playerSelected = playersList.stream().filter((player) -> player.getId().equals(idJogador)).findFirst();
		if (!playerSelected.isPresent()) {
			throw new JogadorNaoEncontradoException();
		} else {
			playerSelected.get().setCapitao(true);
			Long idTime = playerSelected.get().getIdTime();
			teamsList.forEach((team) -> {
				if(team.getId().equals(idTime)) {
					team.setCapitaoTime(idJogador);
				}
			});
		}
	}

	public Long buscarCapitaoDoTime(Long idTime) {
		// Caso o time informado não exista, retornar br.com.codenation.desafio.exceptions.TimeNaoEncontradoException
		// Caso o time informado não tenha um capitão, retornar br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException
		Optional<Time> teamSelected = teamsList.stream().filter((team) -> team.getId().equals(idTime)).findAny();
		if (!teamSelected.isPresent()) {
			throw new TimeNaoEncontradoException();
		} else if (teamSelected.get().getCapitaoTime() == null) {
			throw new CapitaoNaoInformadoException();
		} else {
			return teamSelected.get().getCapitaoTime();
		}
	}

	public String buscarNomeJogador(Long idJogador) {
		// Caso o jogador informado não exista, retornar br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException
		Optional<Jogador> playerSelected = playersList.stream().filter((player) -> player.getId().equals(idJogador)).findAny();
		if (!playerSelected.isPresent()) {
			throw new JogadorNaoEncontradoException();
		} else {
			return playerSelected.get().getNome();
		}
	}

	public String buscarNomeTime(Long idTime) {
		// Caso o time informado não exista, retornar br.com.codenation.desafio.exceptions.TimeNaoEncontradoException
		Optional<Time> teamSelected = teamsList.stream().filter((team) -> team.getId().equals(idTime)).findAny();
		if (!teamSelected.isPresent()) {
			throw new TimeNaoEncontradoException();
		} else {
			return teamSelected.get().getNome();
		}
	}

	public List<Long> buscarJogadoresDoTime(Long idTime) {
		// Caso o time informado não exista, retornar br.com.codenation.desafio.exceptions.TimeNaoEncontradoException
		boolean teamIsPresent = teamsList.stream().anyMatch((team) -> team.getId().equals(idTime));
		if (!teamIsPresent) {
			throw new TimeNaoEncontradoException();
		} else {
			return playersList.stream()
					.filter((player) -> player.getIdTime().equals(idTime))
					.map((Jogador::getId)).collect(toList());
		}
	}

	public Long buscarMelhorJogadorDoTime(Long idTime) {
		// Caso o time informado não exista, retornar br.com.codenation.desafio.exceptions.TimeNaoEncontradoException
		boolean teamIsPresent = teamsList.stream().anyMatch((team) -> team.getId().equals(idTime));
		if (!teamIsPresent) {
			throw new TimeNaoEncontradoException();
		} else {
			List<Jogador> orderedByBestPlayer = playersList
					.stream().sorted((o1, o2) -> o1.getNivelHabilidade() > o2.getNivelHabilidade() ? -1 : 1)
					.collect(toList());
			return orderedByBestPlayer.stream().findFirst().get().getId();
		}
	}

	public Long buscarJogadorMaisVelho(Long idTime) {
		// Caso o time informado não exista, retornar br.com.codenation.desafio.exceptions.TimeNaoEncontradoException
		boolean teamIsPresent = teamsList.stream().anyMatch((team) -> team.getId() == idTime);
		if (!teamIsPresent) {
			throw new TimeNaoEncontradoException();
		} else {
			List<Jogador> orderedByAge = playersList
					.stream().sorted((o1, o2) -> o1.getDataNascimento().compareTo(o2.getDataNascimento()))
					.collect(toList());
			return orderedByAge.stream().findFirst().get().getId();
		}
	}

	public List<Long> buscarTimes() {
		//  Retornar uma lista vazia caso não encontre times cadastrados.
		boolean teamIsPresent = teamsList.isEmpty();
		List<Long> emptyTeams = new ArrayList<>();
		if (teamIsPresent) {
			return emptyTeams;
		} else {
			return teamsList.stream().sorted((o1, o2) -> o1.getId().compareTo(o2.getId()))
					.map((Time::getId)).collect(toList());
		}
	}

	public Long buscarJogadorMaiorSalario(Long idTime) {
		// Caso o time informado não exista, retornar br.com.codenation.desafio.exceptions.TimeNaoEncontradoException
		if (!this.buscarTimes().contains(idTime)) {
			throw new TimeNaoEncontradoException();
		} else {
			List<Jogador> orderedBySalary = playersList.stream()
					.sorted((o1, o2) -> {
						if (o1.getSalario().compareTo(o2.getSalario()) == 0 && o1.getId() < o2.getId()) {
							return -1;
						} else if (o1.getSalario().compareTo(o2.getSalario()) == 0 && o1.getId() > o2.getId()) {
							return 1;
						} else if (o1.getSalario().compareTo(o2.getSalario()) == 1){
							return -1;
						} else {
							return 1;
						}
					})
					.collect(toList());
			return orderedBySalary.stream().findFirst().get().getId();
		}
	}

	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		// Caso o jogador informado não exista, retornar br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException
		Optional<Jogador> playerSelected = playersList.stream().filter((player) -> player.getId().equals(idJogador)).findAny();
		if (!playerSelected.isPresent()) {
			throw new JogadorNaoEncontradoException();
		} else {
			return playerSelected.get().getSalario();
		}
	}

	public List<Long> buscarTopJogadores(Integer top) {
		// Caso não exista nenhum jogador cadastrado, retornar uma lista vazia.
		// List<Jogador> topPlayers = new ArrayList<>(playersList);
		// Posso usar stream().limit(10);
		List<Long> topPlayersList = new ArrayList<>();
		if (playersList.size() == 0) {
			return topPlayersList;
		} else {
			List<Jogador> topPlayersSelected = playersList.stream()
					.sorted((o1, o2) -> o1.getNivelHabilidade() > o2.getNivelHabilidade() ? -1 : 1)
					.collect(toList());
			for (int j = 0; j < top; j += 1) {
				if (topPlayersSelected.get(j).getNivelHabilidade().equals(topPlayersSelected.get(j + 1).getNivelHabilidade())) {
					if (topPlayersSelected.get(j).getId() > topPlayersSelected.get(j + 1).getId()) {
						topPlayersList.add(topPlayersSelected.get(j + 1).getId());
					} else {
						topPlayersList.add(topPlayersSelected.get(j).getId());
					}
				} else {
					topPlayersList.add(topPlayersSelected.get(j).getId());
				}
			}
			return topPlayersList;
		}
	}
}
