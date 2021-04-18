package challenge;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "scripts")
public class Quote {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private Integer episode;

	@Column
	private String episodeName;

	@Column
	private String segment;

	@Column
	private String type;

	@Column
	private String actor;

	@Column
	private String character;

	@Column(name = "detail")
	private String quote;

	@Column
	private Timestamp recordDate;

	@Column
	private String series;

	@Column
	private Timestamp transmissionDate;

}
