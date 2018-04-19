package es.skills2018.api.response;

public interface ContactResponse {
	public class Create implements ContactResponse{
		public boolean success;
		public Create(){}
		public Create(boolean success){
			this.success = success;
		}
		public boolean isSuccess() {
			return success;
		}
		public void setSuccess(boolean success) {
			this.success = success;
		}
		
	}
}
