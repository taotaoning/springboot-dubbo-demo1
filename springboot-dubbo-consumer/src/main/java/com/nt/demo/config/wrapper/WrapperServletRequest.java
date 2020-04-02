package com.nt.demo.config.wrapper;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.util.StreamUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Create by TaoTaoNing
 * 2019/7/24
 * 这个类的作用相当于将请求流中的信息保存起来，后续的对请求流的操作都使用该对象获取流
 * 可以达到类似请求流复制的效果，可以有效避免controller中参数封装之前，请求流中信息就已经被读取出来，
 * 抛出：Required request body is missing  异常
 **/
@Slf4j
public class WrapperServletRequest extends HttpServletRequestWrapper {


    private byte[] buffer;


    public WrapperServletRequest(HttpServletRequest request) {
        super(request);
        try {
            // 读取输入流里面的参数，保存到buff 缓存中
            ServletInputStream inputStream = request.getInputStream();
            int len = 0;
            while ((len = inputStream.read()) != -1){
                System.out.println(len);
            }
            buffer = StreamUtils.copyToByteArray(inputStream);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        return new BufferedServletInputStream(this.buffer);
    }

    class BufferedServletInputStream extends ServletInputStream {

        private ByteArrayInputStream byteArrayInputStream;
        private boolean isFinished = false;

        public BufferedServletInputStream(byte[] buffer) {
            this.byteArrayInputStream = new ByteArrayInputStream(buffer);
        }

        @Override
        public int available() throws IOException {
            return byteArrayInputStream.available();
        }

        @Override
        public int read() {
            int read = byteArrayInputStream.read();
            if (-1 == read) {
                isFinished = true;
            }
            return read;
        }

        @Override
        public int read(byte[] b, int off, int len) throws IOException {
            int read = byteArrayInputStream.read(b, off, len);
            if (-1 == read) {
                isFinished = true;
            }
            return read;
        }

        @Override
        public void setReadListener(ReadListener readListener) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public boolean isFinished() {
            return isFinished;
        }


    }

}
