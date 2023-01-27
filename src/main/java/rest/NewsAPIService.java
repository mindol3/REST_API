package rest;

import model.News;
import model.NewsDAO;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Path("/news")
public class NewsAPIService {
    NewsDAO dao;

    public NewsAPIService() {
        dao = new NewsDAO();
    }
    // 뉴스 등록
    @POST
    @Consumes(MediaType.APPLICATION_JSON) //클라이언트 요청에 포함된 미디어 타입을 지정. JSON을 사용,
    public String addNews(News news){
        try{
            dao.addNews(news); //@Consumes 설정에 따라 HTTP Body에 포함된 JSON문자열이 자동으로 News로 변환.
            //이를 위해서 JSON 문자열의 키와 News 객체의 멤버변수명이 동일해야함.
        } catch (Exception e){
            e.printStackTrace();
            return "News API : 뉴스 등록 실패!!";
        }
        return "News APi : 뉴스 등록됨 !!";
    }

    // 뉴스 상세 정보
    @GET
    @Path("{aid}")
    @Produces(MediaType.APPLICATION_JSON)
    public News getNews(@PathParam("aid") int aid ) {
        News news = null;

        try {
            news = dao.getNews(aid);
        } catch(Exception e){
            e.printStackTrace();
        }
        return news;
    }

  //뉴스삭제하기
  	@DELETE
  	@Path("{aid}")
  	@Consumes(MediaType.APPLICATION_JSON)
  	public void delNews(@PathParam("aid") int aid) {
  		try {
  			dao.delNews(aid);
  		} catch (Exception e) {
  			System.out.println("해당하는 aid값이 존재하지않습니다!");
  			e.printStackTrace();
  		}
  	}
  	
  //뉴스목록보기
  	@GET
  	@Produces(MediaType.APPLICATION_JSON)
  	public List<News> getAll(){
  		List<News> newsList = new ArrayList<>();
  		try {
  			newsList = dao.getAll();
  		} catch (Exception e) {
  			System.out.println("뉴스목록을 불러오는데 실패하였습니다");
  			e.printStackTrace();
  		}
  		return newsList;
  	}
}