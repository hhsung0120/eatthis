package co.kr.heeseong.eatthis.common.service;

import co.kr.heeseong.eatthis.common.domain.model.RequestData;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;


@NoArgsConstructor
@Service
public class ValidationService {

    public <T> T validation(RequestData data) {
        Assert.notNull(data.getParameter(), "the request parameter must not be null");

        String jsonString;
        return null;
//        try{
//
//        }
        //return ObjectConverter.jsonToObject()
    }
}
