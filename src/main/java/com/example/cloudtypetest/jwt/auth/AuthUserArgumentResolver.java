package com.example.cloudtypetest.jwt.auth;

import com.example.cloudtypetest.domain.user.User;
import com.example.cloudtypetest.jwt.TokenProvider;
import com.example.cloudtypetest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@RequiredArgsConstructor
public class AuthUserArgumentResolver implements HandlerMethodArgumentResolver  {

    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        AuthUser authUser = parameter.getParameterAnnotation(AuthUser.class);
        if (authUser == null) return false;
        if (parameter.getParameterType().equals(User.class) == false) {
            return false;
        }
        return true;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
//        Object principal = null;
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null) {
//            principal = authentication.getPrincipal();
//        }
//        if (principal == null || principal.getClass() == String.class) {
//            throw new MemberException(ErrorCode.MEMBER_NOT_FOUND);
//        }
//        MemberDto loginMember = (MemberDto) principal;
//        Member member = MemberSerializer.toMember(loginMember);
//        return member;
        Long userIdx = tokenProvider.getUserIdx();
        System.out.println();
        return userRepository.findById(userIdx).get();
    }
}
