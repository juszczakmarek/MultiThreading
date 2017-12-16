package mju.watki.clientServerCommunication;

import static org.junit.Assert.*;

public class RequestTest {

    Request request;

    @org.junit.Test
    public void getPayloadTest() {
        //given
        String testString = "aBcDeFg";
        String expectedResult = "GfEdCbA";

        //when
        request = new Request(testString);
        String actualResult = request.getPayload();

        //then
        assertEquals("aBcDeFg -> GfEdCbA",expectedResult,actualResult);
    }
}