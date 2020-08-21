package board;

import java.util.ArrayList;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ArticleController {

	Scanner sc = new Scanner(System.in);
	ArrayList<Article> articles = new ArrayList<>();
	ArrayList<Reply> replies = new ArrayList<>();

	int lastId = 3; // 가장 마지막에 추가된 게시물의 게시물 번호
	int lastReplyId = 3; // 가장 마지막에 추가된 댓글의 번호
	
	
	
	ArticleController() { // 생성자 객체 필요한 값을 초기 세팅

		Article article1 = new Article(1, "안녕하세요", "안녕하세요", "익명", "20200817");
		Article article2 = new Article(2, "JAVA 프로그래밍", "JAVA 프로그래밍", "익명", "20200817");
		Article article3 = new Article(3, "어렵지 않아요", "어렵지 않아요", "익명", "20200817");

		articles.add(article1);
		articles.add(article2);
		articles.add(article3);

	}

	void doCommand(String cmd) {
		if (cmd.equals("help")) {

			System.out.println("add : 게시물 등록");
			System.out.println("list : 게시물 목록");
			System.out.println("update : 게시물 수정");
			System.out.println("delete : 게시물 삭제");
			System.out.println("exit : 프로그램 종료");

		} else if (cmd.equals("add")) {

			if (App.loginedMember == null) {
				System.out.println("로그인이 필요한 기능입니다.");
			} else {
				lastId++; // 게시물 번호 자동 증가
				int id = lastId;

				System.out.println("제목을 입력해주세요");
				String title = sc.nextLine();

				System.out.println("내용을 입력해주세요");
				String body = sc.nextLine();
				
				SimpleDateFormat format1 = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
				
				Date today = new Date();
				
				String today1 = format1.format(today);				
				
				Article article = new Article(id, title, body, "익명", today1);
				
				articles.add(article);
			}

		} else if (cmd.equals("list")) {
			printArticles(articles);

		} else if (cmd.equals("update")) {
			System.out.println("수정할 게시물 번호를 입력해주세요.");
			String target = sc.nextLine();
			int targetNo = Integer.parseInt(target);

			int targetIndex = getArticleIndexById(targetNo);

			if (targetIndex == -1) {
				System.out.println("없는 게시물입니다.");
			} else {
				System.out.println("수정할 제목을 입력해주세요.");
				String title = sc.nextLine();
				System.out.println("수정할 내용을 입력해주세요.");
				String body = sc.nextLine();

				Article article = articles.get(targetIndex);
				article.setTitle(title);
				article.setBody(body);

			}

		} else if (cmd.equals("delete")) {
			System.out.println("삭제할 게시물 번호를 입력해주세요.");
			String target = sc.nextLine();
			int targetNo = Integer.parseInt(target);

			int targetIndex = getArticleIndexById(targetNo);

			if (targetIndex == -1) {
				System.out.println("없는 게시물입니다.");
			} else {
				articles.remove(targetIndex);
			}
		} else if (cmd.equals("search")) {
			System.out.println("검색어를 입력해주세요.");
			String keyword = sc.nextLine();

			ArrayList<Article> searchedList = new ArrayList<>();

			for (int i = 0; i < articles.size(); i++) {
				String title = articles.get(i).getTitle();
				if (title.contains(keyword)) {
					searchedList.add(articles.get(i));
				}
			}

			printArticles(searchedList);

		} else if (cmd.equals("read")) {

			System.out.println("상세보기 할 게시물 번호를 입력해주세요.");
			String target = sc.nextLine();
			int targetNo = Integer.parseInt(target);

			int targetIndex = getArticleIndexById(targetNo);

			if (targetIndex == -1) {
				System.out.println("없는 게시물입니다.");
			} else {
				printArticle(articles.get(targetIndex));
				detailMenuStart(targetNo);
			}
		}
	}

	void detailMenuStart(int targetNo) {
		// 상세보기 명령어 프로그램
		System.out.println("상세보기 명령어 입력:");
		while (true) {
			String cmd2 = sc.nextLine();
			if (cmd2.equals("back")) {
				break;
			} else if (cmd2.equals("reply")) {
				System.out.println("댓글 내용을 입력해주세요.");
				String rbody = sc.nextLine();
				System.out.println("댓글이 등록되었습니다.");
				
				// 실제 댓글 데이터 등록

				lastReplyId++; // 댓글 번호 자동 증가
				int id = lastReplyId;

				Reply reply = new Reply(id, targetNo ,rbody, "익명", "20200817");

				replies.add(reply);

				int targetIndex = getArticleIndexById(targetNo);
				printArticle(articles.get(targetIndex));
			}

		}

	}

	int getArticleIndexById(int targetNo) {
		int targetIndex = -1; // 찾는게 없을 때 -1

		for (int i = 0; i < articles.size(); i++) {
			if (articles.get(i).getId() == targetNo) {
				targetIndex = i;
			}
		}

		return targetIndex;
	}

	void printArticles(ArrayList<Article> articles) {
		for (int i = 0; i < articles.size(); i++) {
			System.out.println("번호 : " + articles.get(i).getId());
			System.out.println("제목 : " + articles.get(i).getTitle());
			System.out.println("=====================");

		}
	}

	void printArticle(Article article) {
		System.out.println("======== " + article.getId() + "번 게시물 상세보기 =======");
		System.out.println("번호   : " + article.getId());
		System.out.println("제목   : " + article.getTitle());
		System.out.println("내용   : " + article.getBody());
		System.out.println("작성자 : " + article.getWriter());
		System.out.println("작성일 : " + article.getRegDate());
		// 실제 댓글 데이터를 가져와서 출력
		System.out.println("------- 댓글 -------");
		for (int i = 0; i < replies.size(); i++) {
			if(replies.get(i).getParentId() == article.getId()) {
				System.out.println("내용   :" + replies.get(i).getBody());
				System.out.println("작성자 :" + replies.get(i).getWriter());
				System.out.println("작성일 :" + replies.get(i).getRegDate());
				System.out.println("---------------------");
			}
	
		}

	}
}