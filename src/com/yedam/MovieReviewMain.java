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
				
				break;
			case 2: // 회원정보
				break;
			case 3: // 영화 등록(user_id가 admin인 경우)
				if (!app.myName(uid).equals("관리자")) {
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
