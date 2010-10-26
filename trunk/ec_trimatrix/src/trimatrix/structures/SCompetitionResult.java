package trimatrix.structures;

import trimatrix.db.Competitions;

public class SCompetitionResult {
	public Competitions competition;
	public String resultsId;
	public String resultsTemplate;

	public SCompetitionResult(Competitions competition, String resultsId, String resultsTemplate) {
		this.competition = competition;
		this.resultsId = resultsId;
		this.resultsTemplate = resultsTemplate;
	}
}
