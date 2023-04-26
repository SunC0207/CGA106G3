package CGA106G3.ecpay.controller;

import CGA106G3.com.memoitemord.Entity.Memoitemord;
import CGA106G3.com.memoitemord.Repository.MemoitemordRepository;
import CGA106G3.ecpay.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Enumeration;

@RestController
@RequestMapping("/ecpay")
public class OrderController {

	@Autowired
	OrderService orderService;
	@Autowired
	MemoitemordRepository memoitemordRepository;

	@PostMapping("/checkout")
	public String Checkout(Integer ordno) {
		String aioCheckOutALLForm = orderService.Checkout(ordno);
		// 取得回傳的Form，然後導向綠界付款頁面
		return "redirect:" + aioCheckOutALLForm;
	}
	// 本機無法拿到資料要上http
	@PostMapping("/return")
	public String ecpayReturn(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Enumeration<String> parameterNames = request.getParameterNames();
		String rtnCode = request.getParameter("RtnCode");
		String paymentDate = request.getParameter("PaymentDate");
		String orderno = request.getParameter("MerchantTradeNo").substring(15);
		Memoitemord memoitemord = memoitemordRepository.getReferenceById(Integer.valueOf(orderno));
		// 塞入付款日期
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		memoitemord.setOrddate(LocalDateTime.parse(paymentDate, formatter));
		// 更改付款狀態
		memoitemord.setOrdsta(Integer.valueOf(rtnCode));
		memoitemordRepository.save(memoitemord);
		// 印出所有K,V，參考看看
		while (parameterNames.hasMoreElements()) {
			String paramName = parameterNames.nextElement();
			System.out.println(paramName + ": " + request.getParameter(paramName));
		}
		String url = "/front-product-order_confirmed.html?id=" + orderno;
		response.sendRedirect(url);
		return "1|OK";
	}

	// 訂單查詢 (/ecpay?merchantTradeNo=??????????)
	@GetMapping
	public String getEcpayStatus(String merchantTradeNo) {
		return orderService.checkorder(merchantTradeNo);
	}

}