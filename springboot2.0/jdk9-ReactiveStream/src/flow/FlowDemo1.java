package flow;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

/**
 * Created by mingway on Date:2018-11-29 15:58.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
public class FlowDemo1 {

	public static void main(String[] args) throws InterruptedException {
		//1.定义发布者，发布的数据类型为Integer
		//直接使用jdk自带的SubmissionPublisher，它实现了Publisher接口
		SubmissionPublisher<Integer> publisher = new SubmissionPublisher<Integer>();

		//2.定义订阅者
		Flow.Subscriber<Integer> subscriber = new Flow.Subscriber<Integer>() {
			private Flow.Subscription subscription;
			@Override
			public void onSubscribe(Flow.Subscription subscription) {
				//保存订阅关系，需要用它来给发布者响应
				this.subscription = subscription;

				//请求一个数据
				this.subscription.request(1);
			}

			@Override
			public void onNext(Integer item) {
				//接收一个数据，处理
				System.out.println("接收到的数据：" + item);

				//处理完调用request再请求一个数据
				this.subscription.request(1);

				//如果达到目标，不需要再接收数据，则调用cancel
//				this.subscription.cancel();
			}

			@Override
			public void onError(Throwable throwable) {
				//出现异常时，处理
				throwable.printStackTrace();

				//出现异常，不接受数据
				this.subscription.cancel();
			}

			@Override
			public void onComplete() {

			}
		};

		//3.发布者和订阅者--建立订阅关系
		publisher.subscribe(subscriber);

		//4.生产数据，并发布
		//这里忽略数据生产过程
		int data = 111;
		publisher.submit(data);

		//5.结束后 关闭发布者(正式环境，建议放入finally中)
		publisher.close();

		//主线程延迟停止，否则数据没有消费就退出了（这里测试使用）
		Thread.currentThread().join(1000);
	}
}
