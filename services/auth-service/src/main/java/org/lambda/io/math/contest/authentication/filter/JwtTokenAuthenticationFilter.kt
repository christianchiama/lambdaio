//package org.lambda.io.math.contest.authentication.filter
//
///**
// * @author: Christian Chiama
// * @project: lambdaio
// * @Date: 13/01/23
// * @Time: 07:04
// */
//import com.nilmani.mychat.model.InstaUserDetails
//import com.nilmani.mychat.model.User
//import com.nilmani.mychat.service.JwtTokenProvider
//import com.nilmani.mychat.service.UserService
//import io.jsonwebtoken.Claims
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
//import org.springframework.security.core.context.SecurityContextHolder
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
//import org.springframework.web.filter.OncePerRequestFilter
//import java.io.IOException
//import java.time.LocalDate
//import javax.servlet.FilterChain
//import javax.servlet.ServletException
//import javax.servlet.http.HttpServletRequest
//import javax.servlet.http.HttpServletResponse
//
//
//class JwtTokenAuthenticationFilter : OncePerRequestFilter {
//@Autowired
//private lateinit var jwtConfig: JwtConfig
//@Autowired
//private lateinit var jwtTokenProvider: JwtTokenProvider
//@Autowired
//private lateinit var userService: UserService
//
//        constructor(
//        jwtConfig: JwtConfig,
//        jwtTokenProvider: JwtTokenProvider,
//        userService: UserService
//        ) {
//        this.jwtConfig = jwtConfig
//        this.jwtTokenProvider = jwtTokenProvider
//        this.userService = userService
//        }
//
///**
// * Same contract as for `doFilter`, but guaranteed to be
// * just invoked once per request within a single request thread.
// * See [.shouldNotFilterAsyncDispatch] for details.
// *
// * Provides HttpServletRequest and HttpServletResponse arguments instead of the
// * default ServletRequest and ServletResponse ones.
// */
//@Throws(IOException::class,ServletException::class)
//    override fun doFilterInternal(
//            request: HttpServletRequest,
//            response: HttpServletResponse,
//            filterChain: FilterChain
//            ) {
//            val header:String? = request.getHeader(jwtConfig.header)
//
//            if (header != null) {
//            if (!header.startsWith(jwtConfig.prefix)){
//            filterChain.doFilter(request,response)
//            return
//            }
//            }
//            val token:String? = header?.replace(jwtConfig.prefix,"")
//            if (jwtTokenProvider.validateToken(token)){
//            val claims:Claims = jwtTokenProvider.getClaimsFromJWT(token)
//            val username:String = claims.subject
//
//            val auth:UsernamePasswordAuthenticationToken =
//            userService.findByUserName(username)
//            .map{user:User ->
//            InstaUserDetails(
//            user.id,
//            user.userName,
//            user.password,
//            user.email,
//            user.createdAt,
//            user.updatedAt,
//            user.active,
//            user.userProfile,
//            user.role
//            )
//            }
//            .map { userDetails ->
//            val authentication = UsernamePasswordAuthenticationToken(
//            userDetails, null, userDetails.authorities
//            )
//            authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
//            authentication
//            }
//            .orElse(null)
//            SecurityContextHolder.getContext().authentication=auth
//            }else{
//            SecurityContextHolder.clearContext()
//            }
//
//            filterChain.doFilter(request,response)
//            }
//
//            }
