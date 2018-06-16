package com.luyunfeng.outsource.slotwin.network;

/**
 * Created by luyunfeng on 16/10/8.
 */

public interface API {

    String VERSION = "6.5";

    String LOCAL_PATH = "http://192.168.0.81:8888/api/";
    String BASE_PATH_TEST = "http://test.wannar.com/api/";
    String BASE_PATH_DEV = "https://dev.wannar.com/api/";
    String BASE_PATH_WWW = "https://www.wannar.com/api/";

    // 图片
    String IMAGE_BASE_PATH = "http://us-website.wannar.com/";
    String IMAGE_BASE_PATH_YELP = "http://yelp.wannar.com/";
    String IMAGE_BASE_PATH_HB_BIGGER = "http://photos.hotelbeds.com/giata/bigger/";
    String IMAGE_BASE_PATH_HB = "http://photos.hotelbeds.com/giata/";


    // 配置数据
    String CONFIG = "config/config.php";

    // 搜索
    String SEARCH = "search/solr-search-all.php";

    // 首页推荐
    String RECOMMEND_CONFIG = "config/get-latest-homejson.php";

    // 出发城市
    String DEPARTURE = "destination/departure.php";

    // 天气
    String WEATHER = "weather/weather.php";

    // 用户
    String RESET_PASSWORD = "frontend/user/reset_password.php";
    String REGISTER = "frontend/user/register.php";
    String LOGIN = "frontend/user/login.php";
    String UPDATE_UID_OPENID = "frontend/user/update_uid_openid.php";
    String FEEDBACK = "frontend/user/uid_feedback.php";
    String ADD_CREDIT = "frontend/user/add_credit.php";
    String SEND_VERIFICATION_CODE = "frontend/user/send_verification_code.php";
    String LOGIN_WITH_PHONE = "frontend/user/login_with_phone.php";
    String CONNECT_PHONE = "frontend/user/connect_phone.php";


    // 检查更新
    String CHECK_UPDATE = "util/android/check_update.php";

    // 错误报告
    String BUG_REPORT = "util/android/bug_report.php";

    // 获取景点推荐团
    String GET_SCENIC_TOUR = "frontend/scenic/get_recommend_tour.php";

    // 获取目的地页热门推荐
    String GET_DEPARTURE_RECOMMEND = "frontend/scenic/get_departure_recommend.php";

    // 获取solr列表数据
    String GET_LIST = "list/get-list.php";

    // 获取团列表数据
    String GET_TOUR_LIST = "frontend/tour/get_list.php";

    // 获取团详情
    String GET_TOUR_DETAIL = "frontend/tour/get_desc.php";

    // 获取酒店列表
    String GET_HB_HOTEL_LIST = "hotelbeds/hotel/get_hotel_list.php";

    // 获取景点列表
    String GET_SCENIC_LIST = "frontend/scenic/get_list.php";

    // 获取景点详情
    String SCENIC_DETAIL = "frontend/scenic/get_desc.php";

    // 获取餐馆列表
    String GET_YELP_LIST = "frontend/restaurant/get_list.php";

    // 获取餐馆详情
    String YELP_DETAIL = "frontend/restaurant/get_desc.php";

    // 获取产品评价
    String GET_REVIEW = "review/get-review.php";

    // 收藏夹
    String COLLECT = "collection/collect.php";
    String GET_COLLECTIONS = "collection/get-collections.php";
    String REMOVE_COLLECTION = "collection/remove-collection.php";
    String HAS_COLLECTED = "collection/has-collected.php";

    // 价格计算JS
    String JS_PRICE = "util/js/get_js_price.php";
    // 出行凭证
    String CERTIFICATE_APP_NEW = "https://www.wannar.com/user/certificate_app_new.php";
    // 保存订单
    String PUT_TO_SHOPCART = "backend/shopcart/put_to_shopcart.php";
    // 信用卡订购
    String PLACE_ORDER = "payment/pay/place_order.php";
    // 分笔支付
    String PLACE_REMIT = "payment/pay/place_remit.php";
    // 获取订单
    String GET_TOUR_ORDER_DETAIL = "frontend/order/get_shopcart.php";
    String GET_TOUR_ORDER = "frontend/order/get_tour_orders.php";
    // 用户取消订单
    String CANCEL_ORDER = "backend/shopcart/user_cancel_order.php";
    // 退款
    String UPDATE_ORDER = "backend/shopcart/user_update_confirmed_order.php";
    // 自由补款获取支付状态
    String GET_PAYMENT_STATUS = "frontend/order/get_payment_status.php";
    // 信用卡补款
    String EXTRA_PAYMENT = "payment/pay/extra_payment.php";
    // 订单评价
    String USER_EVALUATION = "frontend/user/save_user_evaluation.php";
    // 上传信用卡图片
    String UPLOAD_CARD_IMAGE = "util/oss/upload_card_image.php";

    String HOTEL_ORDER_LIST = "frontend/order/get_hotel_orders.php";

    String SET_EXTRA = "/set-extra.php";
    String SET_EXTRA_CAR = "/set-extra-car.php";
    String SET_EXTRA_HOTELBEDS = "/set-extra-hotelbeds.php";

    String FP_NOTIFY = "/fp_notify.php";
    String FP_NOTIFY_CAR = "/fp_notify_car.php";
    String FP_NOTIFY_HOTELBEDS = "/fp_notify_hotelbeds.php";

    String NOTIFY = "/notify.php";
    String NOTIFY_CAR = "/notify_car.php";
    String NOTIFY_HOTELBEDS = "/notify_hotelbeds.php";

    String GET_ORDER_STATUS = "frontend/order/get_status.php";
    String GET_CAR_STATUS = "car/get-order-status.php";
    String GET_HOTEL_STATUS = "frontend/order/get_hotel_status.php";

    // 规划
    String UPDATE_PLAN = "plan/set-plan-items-detail.php";
    String DELETE_PLAN = "plan/delete-plan.php";
    String GET_USER_PLAN = "plan/get-plan-items-detail.php";
    @Deprecated
    String RECOMMEND_PLAN = "redis-scenic-plan.php";
    String RESET_PLAN = "plan/reset-plan-items.php";

    // 租车
    String AD_AUTO_COMPLETE = "destination/airport_auto_complete.php";
    String SEARCH_CAR = "car/search-car.php";
    String SELECT_STATION = "car/select-station.php";
    String GET_COMPANY_RULES = "car/get-company-rules.php";
    String BOOK_CAR = "car/book-car-direct.php";
    String GET_CAR_ORDER = "car/user-reservations.php";
    String GET_CAR_ORDER_DETAIL = "car/get-car-order.php";
    String CANCEL_CAR_ORDER = "car/cancel-car-order-for-app.php";


    String HB_DESTINATION_AUTO_COMPLETE = "destination/hotelbeds_destination_auto_complete.php";
    String HB_GET_AVAILABILITY_LIST = "hotelbeds/hotel/get_availability_list.php";
    String HB_GET_DETAILS = "hotelbeds/hotel/get_details.php";
    String HB_BOOK_HOTEL = "hotelbeds/hotel/book_hotel.php";
    String HB_CHECK_RATE = "hotelbeds/hotel/check_rate.php";
    String HB_GET_RATECOMMENTS = "hotelbeds/hotel/get_ratecomments.php";
    String HB_RECOMMEND = "hotelbeds/hotel/get_recommend_hotel.php";

    String STATIC_MAP = "util/map/staticmap.php";
    String GOOGLE_MAP_URL = "http://www.google.cn/maps";
}
