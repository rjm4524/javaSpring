package com.java.dto;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@Data
public class KaKaoDto {
	public long id;
	public String connected_at;
	public Properties properties;
	public Kakao_account kakao_account;
	@Data
	public class Properties {
		public String nickname;
		public String profile_image;
		public String thumbnail_image;
	}// CLASS(Properties)
	@Data
	public class Kakao_account {
		public boolean profile_nickname_needs_agreement;
		public boolean profile_image_needs_agreement;
		public Profile profile;
		public String name;
		public boolean email_needs_agreement;
		public String email;
		public String gender;
		@Data
		public class Profile {
			public String nickname;
			public String thumbnail_image_url;
			public String profile_image_url;
			public boolean is_default_image;
		}// class(Profile)
	}// class(Kakao_account)
}// KakaoDto