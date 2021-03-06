package org.mababio.spring.service;

import java.util.List;
import java.util.Set;

import org.mababio.spring.inter.Node;
import org.mababio.spring.domain.Problem;
import org.mababio.spring.domain.Solution;
import org.mababio.spring.domain.Tag;

import org.mababio.spring.inter.PersistService;
import org.mababio.spring.repo.ProblemRepository;
import org.mababio.spring.repo.SolutionRepository;
import org.mababio.spring.repo.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;


/**
 * @author Workstation
 *
 */
@Service(value="peristService")
@Scope(value="prototype")
public class PersistServiceImpl implements  PersistService {
	
	@Autowired
	private  ProblemRepository proRepository;
	@Autowired
	private  SolutionRepository solRepository;
	@Autowired
	private  TagRepository tagRepository;
	
	
	
	@Override
	public void merger(Node consumer, Node load) {
		consumer.consume(load);
	}

	@Override
	public void merger(Node consumer, Set<? extends Node> load) {
		consumer.consume(load);		
	}
	
	@Override
	public  void  persist(Node node){
		
		if(!node.getContent().isEmpty()){
			if(node instanceof Problem){
				proRepository.save((Problem)node);
			}else if(node instanceof Solution){
				solRepository.save((Solution)node);
			}else if (node instanceof Tag) {
				tagRepository.save((Tag)node);
			}
		}
		
	}
	
	
	/**
	 * @param list
	 * Given a list of <Problem> || <Solution> || <Tag> 
	 * Persisted into DB
	 */
	public void  persist(List<? extends Node> list){
		list.stream().forEach(node -> persist(node));	
	}

	
	

}
