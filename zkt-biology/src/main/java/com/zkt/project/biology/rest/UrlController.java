package com.zkt.project.biology.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

/**
 * 页面URL跳转控制层
 * 
 * @author 
 *
 */
@RestController
@Api(value="UrlController|页面URL跳转控制层")
@RequestMapping(value = "/api/bio/url")
public class UrlController {


	/**
	 * 跳转页面
	 * 
	 * @param request
	 * @param resp
	 * @return
	 */
	@GetMapping(value = "/toindex")
	public String toindex(HttpServletRequest request, HttpServletResponse resp) {
		return "/web/index.jsp";
	}
	
	@GetMapping(value = "/toP1e1")
	public String toP1e1(HttpServletRequest request, HttpServletResponse resp) {
		return "/web/sub/p1/p1e1.jsp";
	}
	
	@GetMapping(value = "/toP1a1")
	public String toP1a1(HttpServletRequest request, HttpServletResponse resp) {
		return "/web/sub/p1/p1a1.jsp";
	}
	
	@GetMapping(value = "/toP1a1Add")
	public String toP1a1Add(HttpServletRequest request, HttpServletResponse resp) {
		return "/web/sub/p1/p1a1Add.jsp";
	}
	
	@GetMapping(value = "/toP1a1Edit")
	public String toP1a1Edit(HttpServletRequest request, HttpServletResponse resp) {
		return "/web/sub/p1/p1a1Edit.jsp";
	}
	
	@GetMapping(value = "/toP1a2")
	public String toP1a2(HttpServletRequest request, HttpServletResponse resp) {
		return "/web/sub/p1/p1a2.jsp";
	}
	
	@GetMapping(value = "/toP1a2Add")
	public String toP1a2Add(HttpServletRequest request, HttpServletResponse resp) {
		return "/web/sub/p1/p1a2Add.jsp";
	}
	
	@GetMapping(value = "/toP1a2Edit")
	public String toP1a2Edit(HttpServletRequest request, HttpServletResponse resp) {
		return "/web/sub/p1/p1a2Edit.jsp";
	}
	
	@GetMapping(value = "/toP1a3")
	public String toP1a3(HttpServletRequest request, HttpServletResponse resp) {
		return "/web/sub/p1/p1a3.jsp";
	}
	
	@GetMapping(value = "/toP1a3Add")
	public String toP1a3Add(HttpServletRequest request, HttpServletResponse resp) {
		return "/web/sub/p1/p1a3Add.jsp";
	}
	
	@GetMapping(value = "/toP1a3Edit")
	public String toP1a3Edit(HttpServletRequest request, HttpServletResponse resp) {
		return "/web/sub/p1/p1a3Edit.jsp";
	}
	
	@GetMapping(value = "/toP1a4")
	public String toP1a4(HttpServletRequest request, HttpServletResponse resp) {
		return "/web/sub/p1/p1a4.jsp";
	}
	
	@GetMapping(value = "/toP1a4Add")
	public String toP1a4Add(HttpServletRequest request, HttpServletResponse resp) {
		return "/web/sub/p1/p1a4Add.jsp";
	}
	
	@GetMapping(value = "/toP1a4Edit")
	public String toP1a4Edit(HttpServletRequest request, HttpServletResponse resp) {
		return "/web/sub/p1/p1a4Edit.jsp";
	}
	
	
	
	@GetMapping(value = "/toP1b1")
	public String toP1b1(HttpServletRequest request, HttpServletResponse resp) {
		return "/web/sub/p1/p1b1.jsp";
	}
	
	@GetMapping(value = "/toP1b5")
	public String toP1b5(HttpServletRequest request, HttpServletResponse resp) {
		return "/web/sub/p1/p1b5.jsp";
	}
	
	@GetMapping(value = "/toP1b5Edit")
	public String toP1b5Edit(HttpServletRequest request, HttpServletResponse resp) {
		return "/web/sub/p1/p1b5Edit.jsp";
	}
	
	@GetMapping(value = "/toP1b1Cage")
	public String toP1b1Cage(HttpServletRequest request, HttpServletResponse resp) {
		return "/web/sub/p1/p1b1Cage.jsp";
	}
	
	@GetMapping(value = "/toP1b2")
	public String toP1b2(HttpServletRequest request, HttpServletResponse resp) {
		return "/web/sub/p1/p1b2.jsp";
	}
	
	@GetMapping(value = "/toP1b23")
	public String toP1b23(HttpServletRequest request, HttpServletResponse resp) {
		return "/web/sub/p1/p1b23.jsp";
	}
	
	@GetMapping(value = "/toP1b2Detail")
	public String toP1b2Detail(HttpServletRequest request, HttpServletResponse resp) {
		return "/web/sub/p1/p1b2Detail.jsp";
	}
	
	@GetMapping(value = "/toP1b2Zhuangxiang")
	public String toP1b2Zhuangxiang(HttpServletRequest request, HttpServletResponse resp) {
		return "/web/sub/p1/p1b2Zhuangxiang.jsp";
	}
	
	@GetMapping(value = "/toP1b2Start")
	public String toP1b2Start(HttpServletRequest request, HttpServletResponse resp) {
		return "/web/sub/p1/p1b2Start.jsp";
	}
	
	@GetMapping(value = "/toP1b3")
	public String toP1b3(HttpServletRequest request, HttpServletResponse resp) {
		return "/web/sub/p1/p1b3.jsp";
	}
	
	@GetMapping(value = "/toP1b4")
	public String toP1b4(HttpServletRequest request, HttpServletResponse resp) {
		return "/web/sub/p1/p1b4.jsp";
	}
	
	@GetMapping(value = "/toP1b3Detail")
	public String toP1b3Detail(HttpServletRequest request, HttpServletResponse resp) {
		return "/web/sub/p1/p1b3Detail.jsp";
	}
	
	@GetMapping(value = "/toP1b3Sign")
	public String toP1b3Sign(HttpServletRequest request, HttpServletResponse resp) {
		return "/web/sub/p1/p1b3Sign.jsp";
	}

	
	
	@GetMapping(value = "/toP1c1")
	public String toP1c1(HttpServletRequest request, HttpServletResponse resp) {
		return "/web/sub/p1/p1c1.jsp";
	}
	
	@GetMapping(value = "/toP1c1Detail")
	public String toP1c1Detail(HttpServletRequest request, HttpServletResponse resp) {
		return "/web/sub/p1/p1c1Detail.jsp";
	}
	
	@GetMapping(value = "/toP1c2")
	public String toP1c2(HttpServletRequest request, HttpServletResponse resp) {
		return "/web/sub/p1/p1c2.jsp";
	}
	
	@GetMapping(value = "/toP1c2Detail")
	public String toP1c2Detail(HttpServletRequest request, HttpServletResponse resp) {
		return "/web/sub/p1/p1c2Detail.jsp";
	}
	
	@GetMapping(value = "/toP1c3")
	public String toP1c3(HttpServletRequest request, HttpServletResponse resp) {
		return "/web/sub/p1/p1c3.jsp";
	}
	
	@GetMapping(value = "/toP1c3DetailReply")
	public String toP1c3DetailReply(HttpServletRequest request, HttpServletResponse resp) {
		return "/web/sub/p1/p1c3DetailReply.jsp";
	}
	
	@GetMapping(value = "/toP1c31")
	public String toP1c31(HttpServletRequest request, HttpServletResponse resp) {
		return "/web/sub/p1/p1c31.jsp";
	}
	
	@GetMapping(value = "/toP1c3Detail")
	public String toP1c3Detail(HttpServletRequest request, HttpServletResponse resp) {
		return "/web/sub/p1/p1c3Detail.jsp";
	}
	
	
	
	@GetMapping(value = "/toP1c4")
	public String toP1c4(HttpServletRequest request, HttpServletResponse resp) {
		return "/web/sub/p1/p1c4.jsp";
	}
	
	@GetMapping(value = "/toP1c4Detail")
	public String toP1c4Detail(HttpServletRequest request, HttpServletResponse resp) {
		return "/web/sub/p1/p1c4Detail.jsp";
	}
	
	
	
	@GetMapping(value = "/toP1c5")
	public String toP1c5(HttpServletRequest request, HttpServletResponse resp) {
		return "/web/sub/p1/p1c5.jsp";
	}
	
	@GetMapping(value = "/toP1c5Detail")
	public String toP1c5Detail(HttpServletRequest request, HttpServletResponse resp) {
		return "/web/sub/p1/p1c5Detail.jsp";
	}
	
	@GetMapping(value = "/toP1c51")
	public String toP1c51(HttpServletRequest request, HttpServletResponse resp) {
		return "/web/sub/p1/p1c51.jsp";
	}
	
	@GetMapping(value = "/toP1c51Detail")
	public String toP1c51Detail(HttpServletRequest request, HttpServletResponse resp) {
		return "/web/sub/p1/p1c51Detail.jsp";
	}
	
	
	@GetMapping(value = "/toP1g1")
	public String toP1g1(HttpServletRequest request, HttpServletResponse resp) {
		return "/web/sub/p1/p1g1.jsp";
	}
	
	@GetMapping(value = "/toP1g1Detail")
	public String toP1g1Detail(HttpServletRequest request, HttpServletResponse resp) {
		return "/web/sub/p1/p1g1Detail.jsp";
	}
	
	@GetMapping(value = "/toP1g2")
	public String toP1g2(HttpServletRequest request, HttpServletResponse resp) {
		return "/web/sub/p1/p1g2.jsp";
	}
	
	@GetMapping(value = "/toP1g2Detail")
	public String toP1g2Detail(HttpServletRequest request, HttpServletResponse resp) {
		return "/web/sub/p1/p1g2Detail.jsp";
	}
	
	@GetMapping(value = "/toP1g11")
	public String toP1g11(HttpServletRequest request, HttpServletResponse resp) {
		return "/web/sub/p1/p1g11.jsp";
	}
	
	@GetMapping(value = "/toP1g1Audited")
	public String toP1g1Audited(HttpServletRequest request, HttpServletResponse resp) {
		return "/web/sub/p1/p1g11Detail.jsp";
	}
	
	
	
	@GetMapping(value = "/toP1f1")
	public String toP1f1(HttpServletRequest request, HttpServletResponse resp) {
		return "/web/sub/p1/p1f1.jsp";
	}
	
	@GetMapping(value = "/toP1f1Echarts")
	public String toP1f1Echarts(HttpServletRequest request, HttpServletResponse resp) {
		return "/web/sub/p1/p1f1Echarts.jsp";
	}
	
	@GetMapping(value = "/toP1f2")
	public String toP1f2(HttpServletRequest request, HttpServletResponse resp) {
		return "/web/sub/p1/p1f2.jsp";
	}
	
	@GetMapping(value = "/toP1f3")
	public String toP1f3(HttpServletRequest request, HttpServletResponse resp) {
		return "/web/sub/p1/p1f3.jsp";
	}
	
	@GetMapping(value = "/toP1f4")
	public String toP1f4(HttpServletRequest request, HttpServletResponse resp) {
		return "/web/sub/p1/p1f4.jsp";
	}
	
	@GetMapping(value = "/toP1f5")
	public String toP1f5(HttpServletRequest request, HttpServletResponse resp) {
		return "/web/sub/p1/p1f5.jsp";
	}
	
	@GetMapping(value = "/toP1f6")
	public String toP1f6(HttpServletRequest request, HttpServletResponse resp) {
		return "/web/sub/p1/p1f6.jsp";
	}
	
	
	
	
	@GetMapping(value = "/toP1h1")
	public String toP1h1(HttpServletRequest request, HttpServletResponse resp) {
		return "/web/sub/p1/p1h1.jsp";
	}
	
	@GetMapping(value = "/toP1h1Add")
	public String toP1h1Add(HttpServletRequest request, HttpServletResponse resp) {
		return "/web/sub/p1/p1h1Add.jsp";
	}
	
	@GetMapping(value = "/toP1h1Edit")
	public String toP1h1Edit(HttpServletRequest request, HttpServletResponse resp) {
		return "/web/sub/p1/p1h1Edit.jsp";
	}
	
	@GetMapping(value = "/toP1h1Detail")
	public String toP1h1Detail(HttpServletRequest request, HttpServletResponse resp) {
		return "/web/sub/p1/p1h1Detail.jsp";
	}
	
	
	
	@GetMapping(value = "/toP1i1")
	public String toP1i1(HttpServletRequest request, HttpServletResponse resp) {
		return "/web/sub/p1/p1i1.jsp";
	}
	
	@GetMapping(value = "/toP1i1Add")
	public String toP1i1Add(HttpServletRequest request, HttpServletResponse resp) {
		return "/web/sub/p1/p1i1Add.jsp";
	}
	
	@GetMapping(value = "/toP1i1Edit")
	public String toP1i1Edit(HttpServletRequest request, HttpServletResponse resp) {
		return "/web/sub/p1/p1i1Edit.jsp";
	}
	
	@GetMapping(value = "/toP1i1Detail")
	public String toP1i1Detail(HttpServletRequest request, HttpServletResponse resp) {
		return "/web/sub/p1/p1i1Detail.jsp";
	}
	
	
	
	@GetMapping(value = "/toP1d1")
	public String toP1d1(HttpServletRequest request, HttpServletResponse resp) {
		return "/web/sub/p1/p1d1.jsp";
	}

	@GetMapping(value = "/toP1d1Add")
	public String toP1d1Add(HttpServletRequest request, HttpServletResponse resp) {
		return "/web/sub/p1/p1d1Add.jsp";
	}
	
	@GetMapping(value = "/toP1d1Edit")
	public String toP1d1Edit(HttpServletRequest request, HttpServletResponse resp) {
		return "/web/sub/p1/p1d1Edit.jsp";
	}
	
	@GetMapping(value = "/toP1d2")
	public String toP1d2(HttpServletRequest request, HttpServletResponse resp) {
		return "/web/sub/p1/p1d2.jsp";
	}

	@GetMapping(value = "/toP1d2Add")
	public String toP1d2Add(HttpServletRequest request, HttpServletResponse resp) {
		return "/web/sub/p1/p1d2Add.jsp";
	}
	
	@GetMapping(value = "/toP1d2Edit")
	public String toP1d2Edit(HttpServletRequest request, HttpServletResponse resp) {
		return "/web/sub/p1/p1d2Edit.jsp";
	}
	
	@GetMapping(value = "/toP1d3")
	public String toP1d3(HttpServletRequest request, HttpServletResponse resp) {
		return "/web/sub/p1/p1d3.jsp";
	}

	@GetMapping(value = "/toP1d3Add")
	public String toP1d3Add(HttpServletRequest request, HttpServletResponse resp) {
		return "/web/sub/p1/p1d3Add.jsp";
	}
	
	@GetMapping(value = "/toP1d3Edit")
	public String toP1d3Edit(HttpServletRequest request, HttpServletResponse resp) {
		return "/web/sub/p1/p1d3Edit.jsp";
	}
	
	@GetMapping(value = "/toP1d4")
	public String toP1d4(HttpServletRequest request, HttpServletResponse resp) {
		return "/web/sub/p1/p1d4.jsp";
	}

	@GetMapping(value = "/toP1d4Add")
	public String toP1d4Add(HttpServletRequest request, HttpServletResponse resp) {
		return "/web/sub/p1/p1d4Add.jsp";
	}
	
	@GetMapping(value = "/toP1d4Edit")
	public String toP1d4Edit(HttpServletRequest request, HttpServletResponse resp) {
		return "/web/sub/p1/p1d4Edit.jsp";
	}
	
	
	
	@GetMapping(value = "/toP1j1")
	public String toP1j1(HttpServletRequest request, HttpServletResponse resp) {
		return "/web/sub/p1/p1j1.jsp";
	}
	
	
	
	@GetMapping(value = "/toP1home")
	public String toP1home(HttpServletRequest request, HttpServletResponse resp) {
		return "/web/sub/p1/home.jsp";
	}
	
	
	
	@GetMapping(value = "/toP1Process")
	public String toP1Process(HttpServletRequest request, HttpServletResponse resp) {
		return "/web/sub/p1/process.jsp";
	}
	
	
	
	@GetMapping(value = "/toP1Trajectory")
	public String toP1Trajectory(HttpServletRequest request, HttpServletResponse resp) {
		return "/web/sub/p1/trajectory.jsp";
	}
	
	
	
}
