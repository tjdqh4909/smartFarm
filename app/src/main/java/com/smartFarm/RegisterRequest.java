package com.smartFarm;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {

    private final static String URL = "http://192.168.219.103/Register.php";
    private Map<String, String> map;

    public RegisterRequest(String user_id, String user_pw, String user_name, int user_age, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(Method.POST,URL, listener, errorListener);

        map = new HashMap<>();
        map.put("userID",user_id);
        map.put("userPassword",user_pw);
        map.put("userName",user_name);
        map.put("userAge", Integer.toString(user_age));
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}

