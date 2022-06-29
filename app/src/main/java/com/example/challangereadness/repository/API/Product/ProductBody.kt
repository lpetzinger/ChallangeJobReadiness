package com.example.challangereadness.repository.API.Product

class ProductBody {
    var id: String = ""
    var site_id: String = ""
    var title: String = ""
    var subtitle: String? = ""
    var seller_id: Any = 0
    var category_id: String = ""
    var official_store_id: Any? = ""
    var price: Float = 0F
    var base_price: Float = 0F
    var original_price: Float = 0F
    var currency_id: String = ""
    var initial_quantity: Any = 0
    var available_quantity: Any = 0
    var sold_quantity: Any = 0
    var sale_terms: List<Any> = listOf()
    var values: List<Any> = listOf()
    var buying_mode: String = ""
    var listing_type_id: String = ""
    var start_time: String = ""
    var stop_time: String = ""
    var condition: String = ""
    var permalink: String = ""
    var thumbnail_id: String = ""
    var thumbnail: String = ""
    var secure_thumbnail: String = ""
    var pictures: List<Pictures> = listOf()
    var video_id: Any? = ""
    var descriptions: List<Any> = listOf()
    var accepts_mercadopago: Boolean = true
    var non_mercado_pago_payment_methods: List<Any> = listOf()

    object shipping {
        var mode: String = ""
        var methods: List<Any> = listOf()
        var tags: List<Any> = listOf()
        var dimensions: Any? = ""
        var local_pick_up: Boolean = false
        var free_shipping: Boolean = true
        var logistic_type: String = "cross_docking"
        var store_pick_up: Boolean = false
    }

    var international_delivery_mode: String = "none"

    object seller_address {
        object city {
            var id: String = ""
            var name: String = ""
        }

        object state {
            var id = "BR-SP"
            var name = "São Paulo"
        }

        object country {
            var id = "BR"
            var name = "Brasil"
        }

        object search_location {
            object neighborhood {
                var id = "TVhYUGFycXVlIEluZHVzdHJpYWxUVmhZVThPa0"
                var name = "Parque Industrial"
            }

            var city = {
                var id = "TVhYU8OjbyBKb3PDqSBkbyBSaW8gUHJldG9UV"
                var name = "São José do Rio Preto"
            }

            object state {
                var id = "TUxCUFNBT085N2E4"
                var name = "São Paulo"
            }
        }

        var id = 1089134583
    }

    var seller_contact: Any? = null
    var location: Any? = ""
    var coverage_areas: List<Any> = listOf()
    var attributes: List<Any> = listOf()
    var warnings: List<Any> = listOf()
    var listing_source = ""
    var variations: List<Any> = listOf()
    var status = "active"
    var sub_status: List<Any> = listOf()
    var tags: List<Any> = listOf()
    var warranty = "Garantia do vendedor: 6 meses"
    var catalog_product_id: Any? = null
    var domain_id = "MLB-DESKTOP_COMPUTERS"
    var parent_item_id: Any? = null
    var differential_pricing: Any? = null
    var deal_ids: List<String> = listOf()
    var automatic_relist: Boolean = false
    var date_created = "2020-07-25T16:34:21.000Z"
    var last_updated = "2022-06-28T11:07:37.000Z"
    var health: Any? = null
    var catalog_listing = false
    var channels: List<Any> = listOf()
}