import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/** 同步的servlet
 * Created by mingway on Date:2018-11-30 15:42.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
@WebServlet(name = "SyncServlet", urlPatterns = "/SyncServlet")
public class SyncServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long t1 = System.currentTimeMillis();
		doSomeThing(request, response);
		System.out.println("sync used: " + (System.currentTimeMillis() - t1));
	}

	private void doSomeThing(HttpServletRequest request, HttpServletResponse response) {
		//模拟耗时操作
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		try {
			response.getWriter().append("done");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}
