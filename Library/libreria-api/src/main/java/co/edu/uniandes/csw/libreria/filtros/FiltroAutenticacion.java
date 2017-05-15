package co.edu.uniandes.csw.libreria.filtros;

import co.edu.uniandes.csw.libreria.exceptions.BusinessLogicException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import javax.crypto.spec.SecretKeySpec;
import javax.ejb.EJB;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

/**
 * Este filtro procesa las excepciones que se arrojan hacia arriba y generan un
 * mecanismo estandar de salida de la excepci&oacute;n
 *
 * @generated
 */
@WebFilter(filterName = "FiltroAutenticacion", urlPatterns = {"/api/*"})
public class FiltroAutenticacion implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    //no realiza ninguna accion
  }

  /**
   * procesa las excepciones y las arroja hacia la capa superior
   */
  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    String usuario = null, jwt = null;
    usuario = ((HttpServletRequest) request).getHeader("usuario");
    jwt = ((HttpServletRequest) request).getHeader("jwt");
    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    Key signingKey = new SecretKeySpec("_DKt2FRF3Woq1Eub-cmRO24iHzxoWw7t2ZFuAG10zzX5K77Py82IZn6VNu_ZXbgi".getBytes(), signatureAlgorithm.getJcaName());

    try {
      if(usuario != null && jwt != null){
        Jwts.parser().setSigningKey(signingKey).parseClaimsJws(jwt);
      }else{
        throw new SignatureException("No autenticado");
      }
      //OK, we can trust this JWT
    } catch (SignatureException e) {
      ((HttpServletResponse)response).setStatus(403);
      try(ServletOutputStream out = response.getOutputStream()){
        out.println("{\"mensaje\":\"El usuario no está autenticado\"}");
      }
      return;
    }
    chain.doFilter(request, response);
  }

  @Override
  public void destroy() {
    //no realiza ninguna accion
  }
}
