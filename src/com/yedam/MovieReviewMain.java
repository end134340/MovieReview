package com.yedam;

import java.util.List;
import java.util.Scanner;

import com.yedam.movie.Movie;
import com.yedam.review.Review;

public class MovieReviewMain {
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		boolean run = true;

		MovieReviewApp app = new MovieReviewApp();
		int menu = 0;
		String uid = "";

		// 회원 로그인 & 가입 부분
		while (true) {
			System.out.println(
					"-----------------------------------------------------------------------------------------");
			System.out.println("         1. 로그인                2. 회원가입");
			System.out.println(
					"-----------------------------------------------------------------------------------------");
			System.out.println("메뉴를 선택해주세요.");
			System.out.print("> ");
			menu = Integer.parseInt(scn.nextLine());
			switch (menu) {
			case 1: // 로그인
				System.out.println("아이디를 입력해주세요.");
				System.out.print("> ");
				uid = scn.nextLine();

				if (uid.isBlank()) {
					System.out.println("아이디는 비워둘 수 없습니다.");
					continue;
				}

				System.out.println("비밀번호를 입력해주세요.");
				System.out.print("> ");
				String pw = scn.nextLine();

				if (pw.isBlank()) {
					System.out.println("비밀번호는 비워둘 수 없습니다.");
				}

				if (!app.login(uid, pw)) {
					System.out.println("로그인이 되지 못했습니다. 다시 입력해주세요.");
					continue;
				} else {
					String name = app.myName(uid);
					System.out.println("\n영화 리뷰 게시판에 오신 것을 환영합니다, " + name + " 님!");
				}
				break;
			case 2: // 회원가입
				System.out.println("아이디를 입력해주세요.");
				System.out.print("> ");
				String id = scn.nextLine();

				if (id.isBlank()) {
					System.out.println("아이디는 비워둘 수 없습니다.");
					continue;
				}
				if (!app.checkId(id)) {
					System.out.println("중복된 아이디는 입력할 수 없습니다.");
					continue;
				}

				System.out.println("비밀번호를 입력해주세요.");
				System.out.print("> ");
				String upw = scn.nextLine();

				if (upw.isBlank()) {
					System.out.println("비밀번호를 비워둘 수 없습니다.");
					continue;
				}

				System.out.println("비밀번호 확인을 입력해주세요.");
				System.out.print("> ");
				String pwck = scn.nextLine();

				if (!upw.equals(pwck)) {
					System.out.println("비밀번호가 일치하지 않습니다!");
					continue;
				}

				System.out.println("사용하실 닉네임을 지정해주세요.");
				System.out.print("> ");
				String name = scn.nextLine();

				if (!app.checkName(name)) {
					System.out.println("이미 등록된 닉네임입니다.");
					continue;
				}

				if (!app.signUp(id, upw, name)) {
					System.out.println("회원가입이 정상적으로 완료되지 못했습니다. 다시 시도해주세요.");
					continue;
				} else {
					System.out.println("회원가입이 정상적으로 완료되셨습니다.");
				}
				break;
			default:
				break;
			}
			break;
		}

		// 영화 리뷰 프로그램 실행 부분
		while (run) {
			if (app.myName(uid).equals("관리자")) {
				System.out.println(
						"-----------------------------------------------------------------------------------------");
				System.out.println("     1. 영화 검색             3. 영화 등록            9. 로그아웃");
				System.out.println(
						"-----------------------------------------------------------------------------------------");
			} else {
				System.out.println(
						"-----------------------------------------------------------------------------------------");
				System.out.println("     1. 영화 검색             2. 회원 정보            9. 로그아웃");
				System.out.println(
						"-----------------------------------------------------------------------------------------");
			}
			System.out.println("메뉴를 선택해주세요.");
			System.out.print("> ");
			menu = Integer.parseInt(scn.nextLine());
			switch (menu) {
			case 1: // 영화 검색
				System.out.println("검색하실 영화의 제목을 입력해주세요.");
				System.out.print("> ");
				String title = scn.nextLine();

				if (app.movieList(title).isEmpty()) {
					System.out.println("찾으시는 제목의 영화가 존재하지 않습니다.");
					break;
				} else {
					System.out.println(
							"-----------------------------------------------------------------------------------------");
					System.out.println(" no.          제목                            감독         장르      개봉년도     별점");
					System.out.println(
							"-----------------------------------------------------------------------------------------");
					for (int i = 0; i < app.movieList(title).size(); i++) {
						System.out.println(app.movieList(title).get(i).showList());
					}
				}

				System.out.println("확인하실 영화의 번호를 입력해주세요. (이전 메뉴로 돌아가기: q)");
				System.out.print("> ");
				String no = scn.nextLine();

				if (no.equals("q")) {
					break;
				} else if (no.isBlank()) {
					System.out.println("영화의 번호를 입력하여 주세요.");
					break;
				}
				System.out.println(
						"-----------------------------------------------------------------------------------------");
				System.out.println(app.selectMovie(Integer.parseInt(no)).selectMovie());

				System.out.println(
						"-----------------------------------------------------------------------------------------");
				System.out.println("     1. 리뷰보기     2. 리뷰 작성     3. 관심 영화 등록     9. 이전메뉴로");
				System.out.println(
						"-----------------------------------------------------------------------------------------");
				System.out.println("메뉴를 선택해주세요.");
				System.out.print("> ");
				menu = Integer.parseInt(scn.nextLine());
				switch (menu) {
				case 1: // 리뷰보기
					if (app.reviewList(Integer.parseInt(no)).size() > 0) {
						System.out.println("no  닉네임    별점                리뷰");
						System.out.println(
								"-----------------------------------------------------------------------------------------");
						for (int i = 0; i < app.reviewList(Integer.parseInt(no)).size(); i++) {
							System.out.println(app.reviewList(Integer.parseInt(no)).get(i).showReviewList());
						}
					} else {
						System.out.println("아직 이 영화에 대한 리뷰가 없습니다.");
					}

					System.out.println(
							"-----------------------------------------------------------------------------------------");
					System.out.println("   1. 리뷰 작성     2. 리뷰 삭제   3. 리뷰 수정    9. 이전메뉴로");
					System.out.println(
							"-----------------------------------------------------------------------------------------");
					System.out.println("메뉴를 선택해주세요.");
					System.out.print("> ");
					menu = Integer.parseInt(scn.nextLine());
					switch (menu) {
					case 1: // 등록
						System.out.println("영화의 별점을 숫자로 입력해주세요. (최대 5점)");
						System.out.print("> ");
						String star = scn.nextLine();

						System.out.println("등록하고 싶은 리뷰를 입력해주세요. 부적절한 언어 사용시 관리자의 재량으로 삭제될 수 있습니다. (공백 포함 최대 50자 이내)");
						System.out.print("> ");
						String review = scn.nextLine();

						if (star.isBlank() || review.isBlank()) {
							System.out.println("이 항목은 비워둘 수 없습니다.");
							break;
						}

						if (app.addReview(Integer.parseInt(no), uid, review, Double.parseDouble(star))) {
							System.out.println("리뷰가 등록되었습니다.");
						} else {
							System.out.println("리뷰가 정상적으로 등록되지 못했습니다. 다시 시도해주세요.");
						}
						break;
					case 2: // 삭제
						if (uid.equals("admin")) {
							System.out.println("삭제하실 리뷰의 번호를 입력해주세요.");
							System.out.print("> ");
							String num = scn.nextLine();

							System.out.println("관리자로 로그인하셨습니다. 리뷰를 삭제하는 사유를 선택해주세요.");
							System.out.println("[1] 부적절한 표현 [2] 스팸");
							System.out.print("> ");
							String reason = scn.nextLine();

							if (num.isBlank() || reason.isBlank()) {
								System.out.println("이 항목을 비워둘 수 없습니다.");
								break;
							}

							if (Integer.parseInt(reason) == 1) {
								reason = "(부적절한 표현 사용으로 삭제된 리뷰입니다.)";
							} else if (Integer.parseInt(reason) == 2) {
								reason = "(스팸으로 의심되어 삭제된 리뷰입니다.)";
							}

							if (app.adminDelete(Integer.parseInt(num), reason)) {
								System.out.println("리뷰가 삭제되었습니다.");
								break;
							} else {
								System.out.println("리뷰가 삭제되지 못했습니다. 다시 시도해주세요.");
								break;
							}
						}

						System.out.println("삭제하실 리뷰의 번호를 입력해주세요. 자신의 리뷰만 삭제할 수 있습니다.");
						System.out.print("> ");
						String num = scn.nextLine();

						if (app.removeReview(Integer.parseInt(num), uid)) {
							System.out.println("리뷰가 삭제되었습니다.");
						} else {
							System.out.println("리뷰가 삭제되지 못했습니다. 다시 시도해주세요.");
						}

						break;
					case 3: // 수정
						System.out.println("수정하실 리뷰의 번호를 입력해주세요. 자신의 리뷰만 수정할 수 있습니다.");
						num = scn.nextLine();
						if (num.isBlank()) {
							System.out.println("리뷰 번호를 비워둘 수 없습니다.");
							break;
						}
						System.out.println("수정하실 별점을 숫자로 입력해주세요. (최대 5점)");
						star = scn.nextLine();
						System.out.println("수정하실 리뷰의 내용을 입력해주세요. (공백 포함 최대 50자 이내)");
						review = scn.nextLine();

						if (app.modifyReview(Integer.parseInt(num), review, Double.parseDouble(star), uid)) {
							System.out.println("리뷰가 수정되었습니다.");
						} else {
							System.out.println("리뷰가 수정되지 못했습니다. 다시 시도해주세요.");
						}
						break;
					case 9: // 이전메뉴로
					default:
						break;
					}

					break;
				case 2: // 리뷰작성
					System.out.println("영화의 별점을 숫자로 입력해주세요. (최대 5점)");
					System.out.print("> ");
					String star = scn.nextLine();

					System.out.println("등록하고 싶은 리뷰를 입력해주세요. 부적절한 언어 사용시 관리자의 재량으로 삭제될 수 있습니다. (공백 포함 최대 50자 이내)");
					System.out.print("> ");
					String review = scn.nextLine();

					if (star.isBlank() || review.isBlank()) {
						System.out.println("이 항목은 비워둘 수 없습니다.");
						break;
					}

					if (app.addReview(Integer.parseInt(no), uid, review, Double.parseDouble(star))) {
						System.out.println(review);
						System.out.println("리뷰가 등록되었습니다.");
					} else {
						System.out.println("리뷰가 정상적으로 등록되지 못했습니다. 다시 시도해주세요.");
					}
					break;
				case 3: // 관심영화 등록
					if (app.favorit(uid, Integer.parseInt(no)).isBlank()) {
						if (app.addFavoritMovie(Integer.parseInt(no), uid)) {
							System.out.println("관심 영화로 등록되었습니다.");
							break;
						} else {
							System.out.println("관심영화로 등록되지 못했습니다. 다시 시도해주세요.");
						}
					} else {
						if (app.modifyFavoritMovie(Integer.parseInt(no), uid)) {
							System.out.println("관심 영화로 등록되었습니다.");
							break;
						} else {
							System.out.println("관심영화로 등록되지 못했습니다. 다시 시도해주세요.");
						}
					}
					break;
				case 9: // 이전메뉴로
				default:
					break;
				}
				break;
			case 2: // 회원정보
				System.out.println(
						"-----------------------------------------------------------------------------------------");
				System.out.println("아이디: " + uid);
				System.out.println("닉네임: " + app.myName(uid));

				System.out.println(
						"-----------------------------------------------------------------------------------------");
				System.out.println("       1. 작성 리뷰 확인            2. 관심 영화 확인               9. 이전메뉴로");
				System.out.println(
						"-----------------------------------------------------------------------------------------");
				System.out.print("> ");
				menu = Integer.parseInt(scn.nextLine());
				switch (menu) {
				case 1: // 작성 리뷰 확인
					List<Review> reviewList = app.ownReviewList(uid);
					if (reviewList.size() > 0) {
						System.out.println(
								"-----------------------------------------------------------------------------------------");
						System.out.println("no  닉네임    별점                리뷰");
						System.out.println(
								"-----------------------------------------------------------------------------------------");
						for (int i = 0; i < reviewList.size(); i++) {
							System.out.println(reviewList.get(i).showReviewList());
						}
					} else {
						System.out.println("작성한 리뷰가 없습니다.");
					}
					break;
				case 2: // 관심 영화 확인
					List<Movie> fmList = app.favoritMovieList(uid);
					if (fmList.size() > 0) {
						System.out.println(
								"-----------------------------------------------------------------------------------------");
						System.out
								.println(" no.          제목                            감독         장르      개봉년도     별점");
						System.out.println(
								"-----------------------------------------------------------------------------------------");
						for (int i = 0; i < fmList.size(); i++) {
							System.out.println(fmList.get(i).showList());
						}
						System.out.println(
								"-----------------------------------------------------------------------------------------");
						System.out.println("  1. 영화 상세 보기                   9. 이전메뉴");
						System.out.println(
								"-----------------------------------------------------------------------------------------");
						menu = Integer.parseInt(scn.nextLine());
						switch (menu) {
						case 1:// 영화 상세보기
							System.out.println("상세히 볼 영화의 번호를 선택해주세요.");
							System.out.print("> ");
							no = scn.nextLine();

							System.out.println(
									"-----------------------------------------------------------------------------------------");
							System.out.println(app.selectMovie(Integer.parseInt(no)).selectMovie());

							break;
						case 9: // 이전메뉴로
						default:
							break;
						}

					} else {
						System.out.println("관심 영화에 등록된 영화가 없습니다.");
					}
					break;
				case 9: // 이전메뉴
				default:
					break;
				}
				break;
			case 3: // 영화 등록(user_id가 admin인 경우)
				if (!app.myName(uid).equals("관리자")) {
					break;
				}

				System.out.println("영화의 제목을 입력해주세요.");
				System.out.print("> ");
				title = scn.nextLine();

				System.out.println("영화의 감독을 입력해주세요.");
				System.out.print("> ");
				String director = scn.nextLine();

				System.out.println("영화의 개봉년도를 입력해주세요. (년도만 입력.)");
				System.out.print("> ");
				String date = scn.nextLine();

				System.out.println("영화의 장르를 입력해주세요.");
				System.out.print("> ");
				String genre = scn.nextLine();

				System.out.println("영화의 간단한 줄거리를 입력해주세요.");
				System.out.print("> ");
				String plot = scn.nextLine();

				if (title.isBlank() || director.isBlank() || date.isBlank() || genre.isBlank() || plot.isBlank()) {
					System.out.println("영화의 정보는 비워둘 수 없습니다.");
					break;
				}

				if (app.addMovie(title, director, date, genre, plot)) {
					System.out.println("영화가 등록되었습니다.");
					break;
				} else {
					System.out.println("영화가 올바르게 등록되지 못했습니다. 다시 시도해주세요.");
				}

				break;
			case 9: // 프로그램 종료
				run = false;
			default:
				break;
			}
		}
		System.out.println("영화 리뷰 프로그램이 종료되었습니다.");
		scn.close();
	} // 메인 끝
}
