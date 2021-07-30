package co.kr.heeseong.eatthis.util;

import co.kr.heeseong.eatthis.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Jwt {

    final static String secretKey = "eatthis_let's_hti_the_jackpot";

    static public String createToken(User user){
        //토큰 구조
        //header.payload.signature

        //토큰 헤더
        Map<String, Object> header = new HashMap<>();
        header.put("typ", "JWT");
        header.put("alg", "HS256");

        //정
        Map<String, Object> payload = new HashMap<>();
        payload.put("data", user);

        //토큰 유효 시간 2시간
        //1000 * 60 = 1분
        //1000 * 60 * 60 = 1시간
        //1000 * 60 * 60 * 2 = 2시간
        int expireTime = 1000 * 60;

        Date expireDate = new Date(); // 토큰 만료 시간
        expireDate.setTime(expireDate.getTime() + expireTime);

        return Jwts.builder()
                .setHeader(header)
                .setClaims(payload)
                .setSubject("accountUser")
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes())
                .compact();
    }

    static public Map<String, Object> verification(String jwt) throws Exception {

        Claims claims = Jwts.parser()
                .setSigningKey(secretKey.getBytes("UTF-8")) // Set Key
                .parseClaimsJws(jwt) // 파싱 및 검증
                .getBody();
        Map<String, Object> claimsData = claims;

        //System.out.println(claimsData);
        //System.out.println(claimsData.get("sub"));
        //System.out.println(claimsData.get("data"));
        //Map<String, Object> data = (Map<String,Object>)claimsData.get("data");
        //System.out.println(data.get("userName"));

        return claimsData;
    }
}
