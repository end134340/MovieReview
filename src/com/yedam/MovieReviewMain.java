package com.yedam;

import java.util.Scanner;

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
				System.out.println("     1. 영화 검색          3. 영화 등록       9. 로그아웃");
				System.out.println(
						"-----------------------------------------------------------------------------------------");
			} else {
				System.out.println(
						"-----------------------------------------------------------------------------------------");
				System.out.println("     1. 영화 검색          2. 회원 정보       9. 로그아웃");
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
				}else if (no.isBlank()) {
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
				switch(menu) {
				case 1: //리뷰보기
					
					break;
				case 2: //리뷰작성
					break;
				case 3: //관심영화 등록
					break;
				case 9: //이전메뉴로
				default: 
					break;
				}
				
				
				break;
			case 2: // 회원정보
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

				if (!app.addMovie(title, director, date, genre, plot)) {
					System.out.println("영화가 올바르게 등록되지 못했습니다. 다시 시도해주세요.");
					break;
				}

				break;
			case 9: // 프로그램 종료
				run = false;
			default:
				break;
			}
		}
		System.out.println("영화 리뷰 프로그램이 종료되었습니다.");

	} // 메인 끝
}
