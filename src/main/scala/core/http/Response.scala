package core.http

object Response {

  import javax.servlet.http.HttpServletResponse
  type Response = core.Response
  type Request = core.Request
  
  def apply(response: HttpServletResponse) = new Response(response)
  
  implicit def httpResponseToResponse(response: HttpServletResponse) = apply(response)
  
  def ok(content: String)(implicit resp: Response) = resp.getWriter().println(content)
  
  def redirect(uri: String)(implicit resp:Response) = resp.sendRedirect(uri)
  
  def forward(uri: String)(implicit req: Request, resp: Response) = req.getRequestDispatcher(uri).forward(req, resp)
  
  def include(uri: String)(implicit req: Request, resp: Response) = req.getRequestDispatcher(uri).include(req, resp)
  
}