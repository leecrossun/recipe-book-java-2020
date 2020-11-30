package controller.recipe;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.user.RegisterUserController;
import controller.user.UserSessionUtils;
import persistence.dao.IngredientDAO;
import persistence.dao.RecipeDAO;
import service.dto.Ingredient;
import service.dto.Recipe;
import service.dto.RecipeIngredient;
import service.dto.RecipeStep;
//파일 업로드를 위한 API를 사용하기 위해...
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import java.io.*; 
//파일 용량 초과에 대한 Exception 클래스를 FileUploadBase 클래스의 Inner 클래스로 처리
import org.apache.commons.fileupload.servlet.*;

public class CreateRecipeController implements Controller{
	private static final Logger log = LoggerFactory.getLogger(RegisterUserController.class);
	private RecipeDAO recipeDAO = new RecipeDAO();
	private IngredientDAO ingredientDAO = new IngredientDAO();
	public CreateRecipeController() {
		try {
			recipeDAO = new RecipeDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();	
		String userId = UserSessionUtils.getLoginUserId(session);
		
		Recipe recipe = new Recipe();
		
		recipe.setUserId(userId);
		String filename = ""; 
		
		boolean check = ServletFileUpload.isMultipartContent(request);
		//전송된 데이터의 인코드 타입이 multipart 인지 여부를 체크한다.
		//만약 multipart가 아니라면 파일 전송을 처리하지 않는다.
		
		if(check) {//파일 전송이 포함된 상태가 맞다면
			String path = request.getServletContext().getRealPath("/upload");
			File dir = new File(path);
			if(!dir.exists()) dir.mkdir();
			//전송된 파일을 저장할 실제 경로를 만든다.
			
			try {
				DiskFileItemFactory factory = new DiskFileItemFactory();
                //파일 전송에 대한 기본적인 설정 Factory 클래스를 생성한다.
                factory.setSizeThreshold(10 * 1024);
                //메모리에 한번에 저장할 데이터의 크기를 설정한다.
                //10kb 씩 메모리에 데이터를 읽어 들인다.
                factory.setRepository(dir);
                //전송된 데이터의 내용을 저장할 임시 폴더를 지정한다.                
    
                ServletFileUpload upload = new ServletFileUpload(factory);
                //Factory 클래스를 통해 실제 업로드 되는 내용을 처리할 객체를 선언한다.
                upload.setSizeMax(10 * 1024 * 1024);
                //업로드 될 파일의 최대 용량을 10MB까지 전송 허용한다.
                upload.setHeaderEncoding("EUC-KR");
                //업로드 되는 내용의 인코딩을 설정한다.
                List<FileItem> items = upload.parseRequest(request);
                //upload 객체에 전송되어 온 모든 데이터를 Collection 객체에 담는다.
                
                for(int i = 0; i < items.size(); ++i) {
                	FileItem item = (FileItem)items.get(i);
                	//commons-fileupload를 사용하여 전송받으면 
                	//모든 parameter는 FileItem 클래스에 하나씩 저장된다.
                	
                	String value = item.getString("euc-kr");
                
                	//넘어온 값에 대한 한글 처리를 한다.
                	
                	if(item.isFormField()) {//일반 폼 데이터라면...                		
                		if(item.getFieldName().equals("recipeName")) recipe.setRecipeName(value);
                		else if(item.getFieldName().equals("summary")) recipe.setSummary(value);
                		else if(item.getFieldName().equals("nation")) recipe.setNation(value);
                		else if(item.getFieldName().equals("difficulty")) recipe.setDifficulty(value);;
                	}
                	else {//파일이라면...
                		if(item.getFieldName().equals("image")) {
                		//key 값이 picture이면 파일 저장을 한다.
                			filename = item.getName();//파일 이름 획득 (자동 한글 처리 됨)
                			if(filename == null || filename.trim().length() == 0) continue;
                			//파일이 전송되어 오지 않았다면 건너 뛴다.
                			filename = filename.substring(filename.lastIndexOf("\\") + 1);
                			//파일 이름이 파일의 전체 경로까지 포함하기 때문에 이름 부분만 추출해야 한다.
                			//실제 C:\Web_Java\aaa.gif라고 하면 aaa.gif만 추출하기 위한 코드이다.
                			File file = new File(dir, filename);
                			item.write(file);
                			recipe.setImage("\"" + dir + "\\" + filename + "\"/>");
                			//파일을 upload 경로에 실제로 저장한다.
                			//FileItem 객체를 통해 바로 출력 저장할 수 있다.
                		}
                	}
                }

			}catch(SizeLimitExceededException e) {
			//업로드 되는 파일의 크기가 지정된 최대 크기를 초과할 때 발생하는 예외처리
				e.printStackTrace();           
            }catch(FileUploadException e) {
            //파일 업로드와 관련되어 발생할 수 있는 예외 처리
                e.printStackTrace();
            }catch(Exception e) {            
                e.printStackTrace();
            }
            
            response.setContentType("text/html;charset=euc-kr");
		
		}
		
		// CreateRecipe
		//처음 레시피를 삽입할때는 report를 0을 기본값으로 교체했습니다.  
		
		// https://jeanette.tistory.com/62 (writeRecipeJSP 수정필요. 참고)
		//ingredient를 추가할 때 ingredientId를 가져오는 방법에 대해 고민을 해보아야 할 것 같습니다. 
		
		List<RecipeIngredient> rcpIngList = new ArrayList<RecipeIngredient>();
		
		String[] ingName = request.getParameterValues("ingName");
		String[] ingId = new String[ingName.length];
		String[] amount = request.getParameterValues("amount");
		String[] unit = request.getParameterValues("unit");
		
		for(int i = 0; i < ingId.length; i++) {
			List<Ingredient> ingList = ingredientDAO.findIngredient(ingName[i]);
			Ingredient ingredient = ingList.get(0);
			ingId[i] = ingredient.getIngredientId();
		}

		
		for(int i = 0; i < ingId.length; i++) {
			RecipeIngredient rcpIng = new RecipeIngredient(null, ingId[i], ingName[i], Double.parseDouble(amount[i]), unit[i]);
			rcpIngList.add(rcpIng);
		}
		recipe.setIngList(rcpIngList);
		
		List<RecipeStep> rcpStepList = new ArrayList<RecipeStep>();
		String[] stepList = request.getParameterValues("stepList");
		
		//recipeId를 아직 모르기 때문에 생성자에서 recipeId를 뺐습니다. 
		for (int i = 0; i < stepList.length; i++) {
			RecipeStep rcpStep = new RecipeStep(i+1, stepList[i]);
			rcpStepList.add(rcpStep);
		}
		
		recipe.setStepList(rcpStepList);
		//여기서 recipeingredient, recipetable에 저장됩니다.
		
		log.debug("Create recipe : {}", recipe.toString());
		
		int generatedKey = recipeDAO.insertRecipe(recipe);
		
		try {
			//int generatedKey = recipeDAO.insertRecipe(recipe);
			// recipe.setRecipeId(String.valueOf(generatedKey));
			//recipeDAO.insertRecipeStep(String.valueOf(generatedKey), rcpStepList);
		
			log.debug("Create recipe : {}", recipe.toString());
/*			request.setAttribute("recipe", recipe);*/
			request.setAttribute("recipeId", String.valueOf(generatedKey));
			/*request.setAttribute("rcpStep", rcpStepList);
			request.setAttribute("rcpIng", rcpIngList); //
			request.setAttribute("review", null);*/
			
			return "/recipe/view";
		}catch(Exception e){
			//return "/recipe/writeRecipe.jsp";
			return "/refrigerator/view";
		}
	}
}


