package com.github.dge1992.jwt;

import com.github.dge1992.jwt.security.impl.Base64SecurityAction;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

/**
 * @author 董感恩
 * @date 2020-02-01 14:46
 * @desc
 */
public class TokenTest {

    public static void main(String[] args) {
//        String token = "eyJhbGciOiJIUzUxMiJ9.eyJyYW5kb21LZXkiOiIzdDg1MmEiLCJzdWIiOiJhZG1pbiIsImV4cCI6MTU4MTE0MTk5NSwiaWF0IjoxNTgwNTM3MTk1fQ.kmXYgwGmlrcXSX_ca0-59NN0uV-DEFrCFZ0h1FCydbRkdFDx09vYwBGFvC1np9noNRAuFKhS5u-siRc5cnDGAA";
//        Claims defaultSecret = Jwts.parser()
//                .setSigningKey("mySecret")
//                .parseClaimsJws(token)
//                .getBody();
//        System.out.println(defaultSecret);

        //base64解码
        String aa = new Base64SecurityAction().unlock("eyJyYW5kb21LZXkiOiJmZHdmOWoiLCJzdWIiOiJhZG1pbiIsImV4cCI6MTU4MTI1MDQ4MywiaWF0IjoxNTgwNjQ1NjgzfQ");
        System.out.println(aa);
    }
}
