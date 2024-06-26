data class CartItem(
    val product: Product,
    var quantity: Int = 1
)

object Cart {
    val items = mutableListOf<CartItem>()

    fun addItem(product: Product) {
        val existingItem = items.find { it.product.id == product.id }
        if (existingItem != null) {
            existingItem.quantity++
        } else {
            items.add(CartItem(product))
        }
    }

    fun removeItem(product: Product) {
        val existingItem = items.find { it.product.id == product.id }
        if (existingItem != null) {
            if (existingItem.quantity > 1) {
                existingItem.quantity--
            } else {
                items.remove(existingItem)
            }
        }
    }

    fun getTotalPrice(): Double {
        return items.sumByDouble { it.product.price * it.quantity }
    }
}
