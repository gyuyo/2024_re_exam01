package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import main.dto.Member;
import main.util.Util;

public class Main {

	static int lastMemberId;
	static List<Member> members;

	static {
		lastMemberId = 0;
		members = new ArrayList<>();
	}

	public static void main(String[] args) {
		System.out.println("==프로그램 시작==");
		Scanner sc = new Scanner(System.in);

		makeTestData();

		while (true) {
			System.out.printf("명령어) ");

			String cmd = sc.nextLine();

			String loginId = null;
			String loginPw = null;
			String loginName = null;

			if (cmd.equals("exit")) {
				break;
			}

			if (cmd.equals("member join")) {

				while (true) {
					System.out.printf("아이디 : ");
					loginId = sc.nextLine();

					if (loginId.length() == 0) {
						System.out.println("아이디는 필수입력정보입니다.");
						continue;
					}
					boolean loginIdDup = false;

					for (Member member : members) {
						if (member.getLoginId().equals(loginId)) {
							loginIdDup = true;
							continue;
						}

					}
					if (loginIdDup) {
						System.out.println("중복된 아이디 입니다.");
						continue;
					}
					break;
				}

				while (true) {
					System.out.printf("비밀번호 : ");
					loginPw = sc.nextLine();
					if (loginPw.length() == 0) {
						System.out.println("비밀번호는 필수입력정보입니다.");
						continue;
					}

					System.out.printf("비밀번호 확인 : ");
					String loginPwChk = sc.nextLine();

					if (loginPw.equals(loginPwChk) == false) {
						System.out.println("비밀번호가 맞지 않습니다.");
						continue;
					}
					break;
				}

				while (true) {
					System.out.printf("이름 : ");
					loginName = sc.nextLine();

					if (loginName.length() == 0) {
						System.out.println("이름은 필수입력정보입니다.");
						continue;
					}
					break;
				}

				lastMemberId++;

				Member member = new Member(lastMemberId, Util.getRegDate(), loginId, loginPw, loginName);

				members.add(member);

				System.out.printf("[ %s ]님의 계정이 생성되었습니다.\n", loginName);
			} else if (cmd.startsWith("member list")) {
				if (members.size() == 0) {
					System.out.println("계정이 존재하지 않습니다.");
					continue;
				}

				List<Member> printMembers = members;

				String keyword = cmd.substring("member list".length()).trim();

				if (keyword.length() > 0) {
					printMembers = new ArrayList<>();
					for (Member member : members) {
						if (member.getLoginId().contains(keyword)) {
							printMembers.add(member);
						}
					}
				}

				if (printMembers.size() == 0) {
					System.out.println("계정이 존재하지 않습니다.");
					continue;
				}

				System.out.println("번호	|		작성일		|	아이디	|	이름");
				for (int i = printMembers.size() - 1; i >= 0; i--) {
					Member member = members.get(i);
					System.out.printf("%d	|	%s	|	%s	|	%s\n", member.getId(), Util.getRegDate(),
							member.getLoginId(), member.getLoginName());
					continue;
				}

			}

		}
		sc.close();
		System.out.println("==프로그램 종료==");
	}

	private static void makeTestData() {
		System.out.println("테스트용 계정 3개 생성했습니다.");
		for (int i = 1; i <= 3; i++) {
			Member member = new Member(++lastMemberId, Util.getRegDate(), "테스트" + i, "테스트" + i, "테스트" + i);
			members.add(member);
			continue;
		}

	}
}
