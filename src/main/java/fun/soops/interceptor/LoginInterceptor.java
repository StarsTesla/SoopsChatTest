package fun.soops.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    //请求之前，再请求方法之前
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle:::" + request.getRequestURL());
        StringBuffer url = request.getRequestURL();

        if(url.toString().endsWith("api/user/login")||url.toString().endsWith("如果有修改密码的页面")||url.toString().endsWith("index.html")){
            return true;
        }else{
            //获取session对象
            Object o = request.getSession().getAttribute("loginUser");
            if(o == null){
                //传参数
                request.setAttribute("msg", "请先登录");
                //没有登录
                request.getRequestDispatcher("api/user/login").forward(request, response);
            }else{
                return true;
            }
        }
        return false;
    }
    //请求通过，方法内代码执行后

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");
    }

    //请求完成，页面渲染完成

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion");
    }
}
