package com.digitalhouse.redirecionamento.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.digitalhouse.redirecionamento.domain.Link;
import com.digitalhouse.redirecionamento.resources.dto.LinkDTO;

@RestController
@RequestMapping
public class LinkController {
	
	private List<Link> links = new ArrayList<>();
	private static Integer numeroId = 0;

	@PostMapping("/link")
	public Integer criarNovoLink(@RequestBody LinkDTO linkDto) {
	
		Link link = new Link(numeroId++, linkDto.getUrl(), true);
		links.add(link);
		
		return link.getId();
	}
	
//	@GetMapping("/link/{id}")
//	public ResponseEntity<String> getUrlDoId(@PathVariable Integer id) {
//		Link link =  links.stream()
//				.filter(l -> l.getId() == id)
//				.findFirst()
//				.get();
//		
//		if(!link.isValid()) return ResponseEntity.notFound().build();
//		
//		link.addRedirecionamento();
//		
//		return ResponseEntity.ok().body(link.getUrl());
//		
//	}
	
	@RequestMapping(value = "/link/{id}", method=RequestMethod.GET)
	public void redirecionamentoUrl(@PathVariable Integer id, HttpServletResponse httpServletResponse) {
	    
		Optional<Link> linkOptional =  links.stream()
				.filter(l -> l.getId() == id)
				.findFirst();
		
		Link link = null;
		
		if(linkOptional.isPresent()) link = linkOptional.get();
		
		if(link == null || !link.isValid()) {
			httpServletResponse.setStatus(404);
		}else {
			link.addRedirecionamento();
			
			httpServletResponse.setHeader("Location", link.getUrl());
		    httpServletResponse.setStatus(302);	
		}
		
	}
	
	@GetMapping("/metrics/{id}")
	public ResponseEntity<String> numeroRedirecionamentos(@PathVariable Integer id){
		Optional<Link> linkOptional =  links.stream()
				.filter(l -> l.getId() == id)
				.findFirst();
		
		Link link = null;
		
		if(linkOptional.isPresent()) link = linkOptional.get();
		
		if(link == null || !link.isValid()) return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok().body("Numero redirecionamentos: "+link.getQuantidadeAcessos());
	}
	
	@PostMapping("/invalidate/{id}")
	public void invalidarUrl(@PathVariable Integer id) {
		Optional<Link> linkOptional =  links.stream()
				.filter(l -> l.getId() == id)
				.findFirst();
		
		Link link = null;
		
		if(linkOptional.isPresent()) {
			link = linkOptional.get();
			link.setValido(false);
		}
	}
	
	
}
