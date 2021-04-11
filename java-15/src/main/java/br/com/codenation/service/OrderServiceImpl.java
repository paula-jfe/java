package br.com.codenation.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.codenation.model.OrderItem;
import br.com.codenation.model.Product;
import br.com.codenation.repository.ProductRepository;
import br.com.codenation.repository.ProductRepositoryImpl;

public class OrderServiceImpl implements OrderService {

	private final ProductRepository productRepository = new ProductRepositoryImpl();
	private static final double isSaleProductPercentage = 0.8;

	@Override
	public Double calculateOrderValue(List<OrderItem> items) {
		return items.stream().mapToDouble((item) -> {
			Optional<Product> product = productRepository.findById(item.getProductId());
			if (product.isPresent()) {
				double orderValue = product.get().getValue() * item.getQuantity();
				if (product.get().getIsSale()) return orderValue * isSaleProductPercentage;
				return orderValue;
			}
			return 0;
		}).sum();
	}

	@Override
	public Set<Product> findProductsById(List<Long> ids) {
		return ids.stream().map((id) -> {
			Optional<Product> product = productRepository.findById(id);
			return product.orElse(null);
		}).collect(Collectors.toSet());
	}

	@Override
	public Double calculateMultipleOrders(List<List<OrderItem>> orders) {
		return orders.stream().mapToDouble(this::calculateOrderValue).sum();
	}

	@Override
	public Map<Boolean, List<Product>> groupProductsBySale(List<Long> productIds) {
		Set<Product> products = this.findProductsById(productIds);
		return products.stream().collect(Collectors.groupingBy(Product::getIsSale));
	}

}