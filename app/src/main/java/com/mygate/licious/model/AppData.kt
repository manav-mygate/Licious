package com.mygate.licious.model

import com.google.gson.annotations.SerializedName

data class AppData(
    @SerializedName("data") val data: Data
)

data class Data(

    @SerializedName("title") val title: String,
    @SerializedName("filters") val filters: List<Filters>,
    @SerializedName("info_message") val info_message: String,
    @SerializedName("info_badge") val info_badge: String,
    @SerializedName("products") val products: List<Products>
)

data class Filters(

    @SerializedName("type") val type: String,
    @SerializedName("title") val title: String
)

/*data class ProductInventory(

    @SerializedName("product_id") val product_id: String,
    @SerializedName("hub_id") val hub_id: Int,
    @SerializedName("cat_id") val cat_id: Int,
    @SerializedName("stock_units") val stock_units: Int,
    @SerializedName("stock_lock") val stock_lock: Int,
    @SerializedName("merchant_delta") val merchant_delta: Int,
    @SerializedName("created_at") val created_at: String,
    @SerializedName("updated_at") val updated_at: String,
    @SerializedName("dispatched_quantity") val dispatched_quantity: Int,
    @SerializedName("rm_buffer") val rm_buffer: Int
)*/

data class ProductMaster(

    @SerializedName("pr_name") val pr_name: String,
    @SerializedName("pr_weight") val pr_weight: String
)

data class ProductMerchantdising(
/*
    @SerializedName("product_id") val product_id: String,
    @SerializedName("hub_id") val hub_id: Int,
    @SerializedName("city_id") val city_id: Int,
    @SerializedName("description") val description: String,
    @SerializedName("short_description") val short_description: String,
    @SerializedName("meta_title") val meta_title: String,
    @SerializedName("meta_description") val meta_description: String,
    @SerializedName("meta_keywords") val meta_meta_keywordswords: String,*/
    @SerializedName("pr_image") val pr_image: String
/*    @SerializedName("pr_image_bucket") val pr_image_bucket: String,
    @SerializedName("pr_tags") val pr_tags: String,
    @SerializedName("usp_desc") val usp_desc: String,
    @SerializedName("msite_desc") val msite_desc: String,
    @SerializedName("wt_msg") val wt_msg: String,
    @SerializedName("product_delivery_type") val product_delivery_type: String,
    @SerializedName("merchandise_name") val merchandise_name: String,
    @SerializedName("type") val type: String,
    @SerializedName("cut_off_time") val cut_off_time: Int,
    @SerializedName("slider_images") val slider_images: String,
    @SerializedName("product_shortname") val product_shortname: String,
    @SerializedName("no_of_piceces") val no_of_piceces: String,
    @SerializedName("serves") val serves: Int,
    @SerializedName("cooking_time") val cooking_time: String,
    @SerializedName("best_before") val best_before: String,
    @SerializedName("product_header_tags") val product_header_tags: String,
    @SerializedName("pdp_gross_wt") val pdp_gross_wt: String,
    @SerializedName("pdp_net_wt") val pdp_net_wt: String,
    @SerializedName("pdp_pieces_img_url") val pdp_pieces_img_url: String,
    @SerializedName("pdp_serves_img_url") val pdp_serves_img_url: String,
    @SerializedName("pdp_cooktime_img_url") val pdp_cooktime_img_url: String,
    @SerializedName("pdp_bestbefore_img_url") val pdp_bestbefore_img_url: String,
    @SerializedName("gross_wt_img_pdp") val gross_wt_img_pdp: String,
    @SerializedName("net_wt_img_pdp") val net_wt_img_pdp: String,
    @SerializedName("display_order") val display_order: Int,
    @SerializedName("created_at") val created_at: String,
    @SerializedName("updated_at") val updated_at: String,
    @SerializedName("score") val score: Int,
    @SerializedName("inv_sort") val inv_sort: Int,
    @SerializedName("count_sort") val count_sort: Int*/
)

data class ProductPricing(
  /*  @SerializedName("product_id") val product_id: String,
    @SerializedName("city_id") val city_id: Int,
    @SerializedName("hub_id") val hub_id: Int,*/
    @SerializedName("base_price") val base_price: Double
/*    @SerializedName("price_gram") val price_gram: Double,
    @SerializedName("unit_gram") val unit_gram: Int,
    @SerializedName("cgst") val cgst: Int,
    @SerializedName("sgst") val sgst: Int,
    @SerializedName("created_at") val created_at: String,
    @SerializedName("updated_at") val updated_at: String*/
)

data class Products(

    @SerializedName("product_master") val product_master: ProductMaster,
    @SerializedName("product_merchantdising") val product_merchantdising: ProductMerchantdising,
    @SerializedName("product_pricing") val product_pricing: ProductPricing
    //@SerializedName("product_inventory") val product_inventory: ProductInventory
)

data class DataToList(
    val imageUrl: String,
    val itemName: String,
    val price: String,
    val weigth: String
)

data class ProcessedData(
    val list: List<DataToList>,
    val title:String,
    val infoMessage:String,
    val infoBadge:String,
    val filterList:List<Filters>

)
