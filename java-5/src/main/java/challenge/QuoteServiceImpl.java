package challenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuoteServiceImpl implements QuoteService {

	@Autowired
	private QuoteRepository quoteRepository;

	@Override
	public Quote getQuote() {
		return quoteRepository.getQuote();
	}

	@Override
	public Quote getQuoteByActor(String actor) {
		return quoteRepository.getQuoteByActor(actor);
	}

}
