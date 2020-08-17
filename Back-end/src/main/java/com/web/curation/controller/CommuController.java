package com.web.curation.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.dto.CoGoodDto;
import com.web.curation.dto.CoReplyDto;
import com.web.curation.dto.CommuDto;
import com.web.curation.dto.CommuReply;
import com.web.curation.dto.CommuUpload;
import com.web.curation.dto.UserDto;
import com.web.curation.service.CommuService;
import com.web.curation.service.UserService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/community")
public class CommuController {

	@Autowired
	private UserService userService;
	@Autowired
	private CommuService commuService;

	// 게시글 전체 리스트 출력
	@ApiOperation("게시글 전체 리스트 출력")
	@GetMapping("/list")
	public ResponseEntity<List<CommuDto>> getCommuList(@RequestParam int page, @RequestParam int no,
			HttpServletRequest request) {
		UserDto udto = userService.getTokenInfo(request);
		if (udto.getU_name().equals("F")) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} else {
			List<CommuDto> list = new ArrayList<>();
			if (no == 1) { // 좋아요순
				list = commuService.getCommuList(udto.getU_email(), "good_cnt");
			} else { // 최신순.
				list = commuService.getCommuList(udto.getU_email(), "co_idx");
			}

			if (list != null) {
				List<CommuDto> showList = new ArrayList<>();
				int lastPageRemain = list.size() % 5;
				int lastPage = list.size() - lastPageRemain;
				page = 5 * page - 5;
				// 5개씩 보여주기
				if (page < lastPage) {
					for (int i = page; i < page + 5; i++) {

						showList.add(list.get(i));
					}
				} else if (page == lastPage) {
					for (int i = page; i < page + lastPageRemain; i++) {
						showList.add(list.get(i));
					}
				}
				return new ResponseEntity<List<CommuDto>>(showList, HttpStatus.OK);

			} else {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
		}
	}

	//게시물 등록
	@ApiOperation("게시물 등록")
	@PostMapping("/add")
	public ResponseEntity<String> commuadd(CommuUpload up, HttpServletRequest request)
			throws IllegalStateException, IOException {
		UserDto udto = userService.getTokenInfo(request);
		if (udto.getU_name().equals("F")) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} else {
			CommuDto dto = new CommuDto();
			if(up.getCo_img()!=null) {
				String saveUrl = "/home/ubuntu/s03p13b202/front-end/dist/img/" + up.getCo_img().getOriginalFilename();
				File file = new File(saveUrl);
				up.getCo_img().transferTo(file);
				dto.setCo_img(saveUrl);
			}

			dto.setCo_content(up.getCo_content());
			dto.setCo_email(udto.getU_email());
			
			if (commuService.addCommu(dto)) {
				return new ResponseEntity<String>("success", HttpStatus.OK);
			} 
			
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

	// 게시글 삭제
	@ApiOperation("게시글 삭제")
	@DeleteMapping("/delete/{co_idx}")
	public ResponseEntity<List<CommuDto>> deleteCommu(@PathVariable int co_idx, @RequestParam int page,
			@RequestParam int no, HttpServletRequest request) {
		UserDto udto = userService.getTokenInfo(request);
		if (udto.getU_name().equals("F")) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} else {
			//이미지 처리 
			String imgurl = commuService.getImgUrl(co_idx);
			if(imgurl!=null) {
				File file = new File(imgurl);
				file.delete();
			}
			
			if (commuService.deleteCommu(co_idx)) {
				List<CommuDto> list = new ArrayList<>();
				if (no == 1) { // 좋아요순
					list = commuService.getCommuList(udto.getU_email(), "good_cnt");
				} else { // 최신순.
					list = commuService.getCommuList(udto.getU_email(), "co_idx");
				}
				List<CommuDto> showList = new ArrayList<>();
				if (list != null) {
					int lastPageRemain = list.size() % 5;
					int lastPage = list.size() - lastPageRemain;
					page = 5 * page - 5;
					// 5개씩 보여주기
					if (page < lastPage) {
						for (int i = 0; i < page + 5; i++) {
							showList.add(list.get(i));
						}
					} else if (page == lastPage) {
						for (int i = 0; i < page + lastPageRemain; i++) {
							showList.add(list.get(i));
						}
					} else {
						return new ResponseEntity<List<CommuDto>>(list, HttpStatus.OK);
					}
					return new ResponseEntity<List<CommuDto>>(showList, HttpStatus.OK);
				} else {
					return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
				}
			} else {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
		}
	}

	// 게시글 디테일에서 삭제
	@ApiOperation("게시글 디테일에서 삭제")
	@DeleteMapping("/detaildelete/{co_idx}")
	public ResponseEntity<String> deleteCommu(@PathVariable int co_idx, HttpServletRequest request) {
		UserDto udto = userService.getTokenInfo(request);
		if (udto.getU_name().equals("F")) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} else {
			//이미지 처리 
			String imgurl = commuService.getImgUrl(co_idx);
			if(imgurl!=null) {
				File file = new File(imgurl);
				file.delete();
			}
			
			if (commuService.deleteCommu(co_idx)) {
				return new ResponseEntity<String>("디테일에서 삭제 완료", HttpStatus.OK);
			}
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	// 게시글 디테일
	@ApiOperation("게시글 디테일 ")
	@GetMapping("/detail/{co_idx}")
	public ResponseEntity<CommuDto> getDetail(@PathVariable int co_idx, HttpServletRequest request) {
		UserDto udto = userService.getTokenInfo(request);
		if (udto.getU_name().equals("F")) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} else {
			CoGoodDto gdto = new CoGoodDto(co_idx, udto.getU_email());
			CommuDto detailData = commuService.getCommuDetail(gdto);
			if (detailData != null) {
				return new ResponseEntity<CommuDto>(detailData, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
		}
	}

	// 디테일 댓글 리스트
	@ApiOperation("디테일 댓글 리스트 ")
	@GetMapping("/detail/replylist/{co_idx}")
	public ResponseEntity<List<CommuReply>> getDetailReplyList(@PathVariable int co_idx,
			@RequestParam("page") int page) {
		List<CommuReply> showList = new ArrayList<>();
		try {
			List<CommuReply> list = commuService.getDetailReplyList(co_idx);

			int lastPageRemain = list.size() % 5;
			int lastPage = list.size() - lastPageRemain;
			page = 5 * page - 5;
			// 5개씩 보여주기
			if (page < lastPage) {
				for (int i = page; i < page + 5; i++) {
					showList.add(list.get(i));
				}
			} else if (page == lastPage) {
				for (int i = page; i < page + lastPageRemain; i++) {
					showList.add(list.get(i));
				}
			}
			return new ResponseEntity<List<CommuReply>>(showList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.OK);
		}
	}

	// 게시물 디테일 수정
	@ApiOperation("게시물 디테일 수정") 
	@PutMapping("/update")
	public ResponseEntity<String> updateDetail( CommuUpload up, HttpServletRequest request) throws IllegalStateException, IOException {
		UserDto udto = userService.getTokenInfo(request);
		if (udto.getU_name().equals("F")) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} else {
			//이미지 처리 
			CommuDto dto = new CommuDto();
			dto.setCo_idx(up.getCo_idx());
			if(up.getCo_img()!=null) { //이미지 수정이라면 
				String imgurl = commuService.getImgUrl(up.getCo_idx());
				if(imgurl!=null) { //이미지 찾아서 삭제 
					File file = new File(imgurl);
					file.delete();
				}
				
				String saveUrl = "/home/ubuntu/s03p13b202/front-end/dist/img/" + up.getCo_img().getOriginalFilename();
				File file = new File(saveUrl);
				up.getCo_img().transferTo(file);
				dto.setCo_img(saveUrl);
			}
			if(up.getCo_content()!=null) {
				dto.setCo_content(up.getCo_content());
			}
			
			if (commuService.updateDetail(dto)) {
				return new ResponseEntity<String>("디테일에서 게시글 수정 완료", HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("디테일에서 게시글 수정 에러 ", HttpStatus.NOT_FOUND);
			}
		}
	}

	// 댓글 추가
	@ApiOperation("댓글 추가")
	@PostMapping("/replyadd")
	public ResponseEntity<List<CommuReply>> addCommuReply(@RequestBody CoReplyDto dto, @RequestParam("page") int page,
			HttpServletRequest request) {
		UserDto udto = userService.getTokenInfo(request);
		if (udto.getU_name().equals("F")) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} else {
			dto.setU_email(udto.getU_email());
			dto.setCo_idx(dto.getCo_idx());
			if (commuService.addCommuReply(dto)) {
				List<CommuReply> list = commuService.getDetailReplyList(dto.getCo_idx());
				List<CommuReply> showList = new ArrayList<>();
				if (list != null) {
					int lastPageRemain = list.size() % 5;
					int lastPage = list.size() - lastPageRemain;
					page = 5 * page - 5;
					// 5개씩 보여주기
					if (page < lastPage) {
						for (int i = 0; i < page + 5; i++) {
							showList.add(list.get(i));
						}
					} else if (page == lastPage) {
						for (int i = 0; i < page + lastPageRemain; i++) {
							showList.add(list.get(i));
						}
					} else {
						return new ResponseEntity<List<CommuReply>>(list, HttpStatus.OK);
					}
					return new ResponseEntity<List<CommuReply>>(showList, HttpStatus.OK);
				}
			}
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

	// 댓글 삭제
	@ApiOperation("댓글 삭제")
	@DeleteMapping("/replydelete/{co_idx}/{cr_idx}")
	public ResponseEntity<List<CommuReply>> deleteCommuReply(@PathVariable int co_idx, @PathVariable int cr_idx,
			@RequestParam int page, HttpServletRequest request) {
		UserDto udto = userService.getTokenInfo(request);
		if (udto.getU_name().equals("F")) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} else {
			CoReplyDto dto = new CoReplyDto();
			dto.setCo_idx(co_idx);
			dto.setCr_idx(cr_idx);
			List<CommuReply> showList = new ArrayList<>();
			if (commuService.deleteCommuReply(dto)) {
				List<CommuReply> list = commuService.getDetailReplyList(co_idx);
				if (list != null) {
					int lastPageRemain = list.size() % 5;
					int lastPage = list.size() - lastPageRemain;
					page = 5 * page - 5;
					// 5개씩 보여주기
					if (page < lastPage) {
						for (int i = 0; i < page + 5; i++) {
							showList.add(list.get(i));
						}
					} else if (page == lastPage) {
						for (int i = 0; i < page + lastPageRemain; i++) {
							showList.add(list.get(i));
						}
					} else {
						return new ResponseEntity<List<CommuReply>>(list, HttpStatus.OK);
					}
					return new ResponseEntity<List<CommuReply>>(showList, HttpStatus.OK);
				}
			}
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	// 게시글 좋아요 클릭
	@ApiOperation("게시글 좋아요 ")
	@GetMapping("/good/{co_idx}")
	public ResponseEntity<Integer> getGood(@PathVariable int co_idx, @RequestParam int isgood,
			HttpServletRequest request) {
		UserDto udto = userService.getTokenInfo(request);
		if (udto.getU_name().equals("F")) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} else {
			CoGoodDto dto = new CoGoodDto(co_idx, udto.getU_email());
			if (isgood == 1) { // 좋아요 취소
				if (commuService.clickGoodCancel(dto)) {
					isgood = 0;
				}
			} else { // 좋아요 선택
				if (commuService.clickGood(dto)) {
					isgood = 1;
				}
			}
			return new ResponseEntity<Integer>(isgood, HttpStatus.OK);
		}
	}
}
