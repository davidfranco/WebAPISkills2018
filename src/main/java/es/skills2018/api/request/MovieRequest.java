package es.skills2018.api.request;

public interface MovieRequest {
	public class Put implements MovieRequest{
		public int ranking;
		public double score;
		public int year;
		public String starCast;
		public Put(){}
		public Put(int ranking, double score, int year, String starCast) {
			this.ranking = ranking;
			this.score = score;
			this.year = year;
			this.starCast = starCast;
		}
		public double getScore() {
			return score;
		}
		public void setScore(double score) {
			this.score = score;
		}
		public int getYear() {
			return year;
		}
		public void setYear(int year) {
			this.year = year;
		}
		public String getStarCast() {
			return starCast;
		}
		public void setStarCast(String starCast) {
			this.starCast = starCast;
		}
		public int getRanking() {
			return ranking;
		}
		public void setRanking(int ranking) {
			this.ranking = ranking;
		}
		
	}

	public class Search implements MovieRequest{
		public String text;
		public int order;
		public int page;
		public Search(){}
		public Search(String text, int order, int page) {
			this.text = text;
			this.order = order;
			this.page = page;
		}
		public int getOrder() {
			return order;
		}
		public void setOrder(int order) {
			this.order = order;
		}
		public int getPage() {
			return page;
		}
		public void setPage(int page) {
			this.page = page;
		}
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
	}
}
