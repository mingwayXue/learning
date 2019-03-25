package com.xue.demo.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin.server.EnableZipkinServer;

/**
 * springboot2.0不支持自建ZipkinServer,所以将springboot降级
 * 注：降级后springboot的版本为1.*，与其它应用版本不符，导致同样时127.0.0.1:9411的地址，但是zipkin监控不到
 * 所以：zipkin2.*的官方推荐直接使用jar包，不要自定义zipkin-server服务，因此，下载zipkin的jar包即可（https://search.maven.org/remote_content?g=io.zipkin.java&a=zipkin-server&v=LATEST&c=exec ）
 */
@SpringBootApplication
@EnableZipkinServer
public class ZipkinServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZipkinServerApplication.class, args);
	}
}
