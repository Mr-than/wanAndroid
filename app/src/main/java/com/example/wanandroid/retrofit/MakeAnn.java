package com.example.wanandroid.retrofit;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.PatternSyntaxException;

public class MakeAnn {

    public static Type t(String baseurl,String className,String methodName,String arg) {
        StringBuilder get=new StringBuilder();
        StringBuilder post=new StringBuilder();
        StringBuilder value=new StringBuilder();
        List<String> headerKey=new ArrayList<>();
        List<String> headerValue=new ArrayList<>();

        List<String> valueKey=new ArrayList<>();
        List<String> valueValue=new ArrayList<>();


        StringBuilder endUrl=new StringBuilder();

        URL url;
        HttpsURLConnection httpsURLConnection = null;

        Annotation[] annotations =null;

        String mutablePath = null;
        String mutablePathName=null;


        try {

            if(arg==null) {
                annotations = Class.forName(className).getMethod(methodName).getAnnotations();
            }else {
                annotations = Class.forName(className).getMethod(methodName,String.class).getAnnotations();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(annotations!=null&&annotations.length>0) {

            for (Annotation annotation : annotations) {
                if(annotation instanceof PATH){
                    if(arg!=null){
                        mutablePath=arg;
                        mutablePathName=((PATH)(annotation)).value();
                    }
                }
                if (annotation instanceof GET) {
                    if (get.length() > 0) {
                        get = new StringBuilder();
                    }
                    get.append(((GET) annotation).port());
                }
                if (annotation instanceof POST) {
                    if (post.length() > 0) {
                        post = new StringBuilder();
                    }
                    post.append(((POST) annotation).port());
                }
                if(annotation instanceof Header){
                    for (int i = 0; i < ((Headers)annotation).value().length; i++) {
                        headerKey.add((((Headers)annotation).value()[i]).key());
                        headerValue.add((((Headers)annotation).value()[i]).value());
                    }

                }
                if(annotation instanceof Values){
                    for (int i = 0; i <((Values)annotation).value().length; i++) {
                        valueKey.add((((Values)annotation).value()[i]).key());
                        valueValue.add((((Values)annotation).value()[i]).value());
                    }
                }
            }

            for (int i = 0; i < valueKey.size(); i++) {
                value.append(valueKey.get(i)).append("=").append(valueValue.get(i)).append("&");
            }
            if(value.length()>0)
            value= new StringBuilder(value.substring(0, value.length() - 1));

            if(get.length()>0){

                if(value.length()==2) {
                    endUrl.append(baseurl).append(get);
                } else {

                    endUrl.append(baseurl).append(get).append("?").append(value);
                }


                try {

                    if(mutablePathName!=null){

                     String temp=endUrl.toString();
                     endUrl=new StringBuilder();
                     endUrl.append(temp.replaceAll("\\{" + mutablePathName + "\\}",mutablePath));
                    }
                    url=new URL(endUrl.toString());
                    httpsURLConnection= (HttpsURLConnection) url.openConnection();
                    httpsURLConnection.setRequestMethod("GET");
                    httpsURLConnection.setConnectTimeout(8000);

                } catch (IOException | PatternSyntaxException e) {
                    e.printStackTrace();
                }

                if (httpsURLConnection != null) {
                    httpsURLConnection.setDoInput(true);
                }

                for (int i = 0; i < headerKey.size(); i++) {
                    if (httpsURLConnection != null) {
                        httpsURLConnection.setRequestProperty(headerKey.get(i),headerValue.get(i));
                    }
                }

                return new Type(httpsURLConnection,null);

            }else {

                endUrl.append(baseurl).append(post);

                try {
                    if(mutablePathName!=null){

                        String temp=endUrl.toString();
                        endUrl=new StringBuilder();
                        endUrl.append(temp.replaceAll("{"+methodName+"}",mutablePath.toString()));

                    }

                    url=new URL(endUrl.toString());
                    httpsURLConnection= (HttpsURLConnection) url.openConnection();
                    httpsURLConnection.setRequestMethod("POST");
                    httpsURLConnection.setConnectTimeout(8000);

                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (httpsURLConnection != null) {
                    httpsURLConnection.setDoInput(true);
                    httpsURLConnection.setDoOutput(true);
                }

                for (int i = 0; i < headerKey.size(); i++) {
                    if (httpsURLConnection != null) {
                        httpsURLConnection.setRequestProperty(headerKey.get(i),headerValue.get(i));
                    }
                }



                return new Type(httpsURLConnection,value.toString());
            }



        }else {
            throw new RuntimeException("方法没有定义任何注解");
        }
    }

    public static class Type{

        public Type(HttpsURLConnection httpsURLConnection, String value) {
            this.httpsURLConnection = httpsURLConnection;
            this.value = value;
        }

        public HttpsURLConnection httpsURLConnection;
        public String value;

    }
}
