package frances.emart.com.emartorderservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Data
public class RabbitMQConfig {
    @Value("${emart.rabbitmq.queuef}")
	String queueNamef;

	@Value("${emart.rabbitmq.queuei}")
	String queueNamei;

	@Value("${emart.rabbitmq.exchange}")
	String exchange;

	@Value("${emart.rabbitmq.routingkey}")
	private String routingkey;

	@Bean
	Queue queuef() {
		return new Queue(queueNamef, false);
	}


	@Bean
	Queue queuei() {
		return new Queue(queueNamei, false);
	}

	@Bean
	DirectExchange exchange() {
		return new DirectExchange(exchange);
	}

	@Bean
	Binding bindingi(Queue queuei, DirectExchange exchange) {
		return BindingBuilder.bind(queuei).to(exchange).with(routingkey);
	}

	@Bean
	Binding bindingf(Queue queuef, DirectExchange exchange) {
		return BindingBuilder.bind(queuef).to(exchange).with(routingkey);
	}

	
	// // @Bean
	// // public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
	// // 	final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
	// // 	rabbitTemplate.setMessageConverter(jsonMessageConverter());
	// // 	return rabbitTemplate;
	// }
}