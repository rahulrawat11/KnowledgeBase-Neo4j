package org.mababio.spring.domain;

import java.util.HashSet;
import java.util.Set;

import org.mababio.spring.inter.Node;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

@NodeEntity
public class Tag extends Node {
	
	private static final long serialVersionUID = 1L;
	
	@RelatedTo(type="Associated_To")
	private Set<Tag> tags = new HashSet<Tag>();

	public Set<Tag> getTags() {
		return tags;
	}
	
	
	/* Does not consume if the content of node is empty*/
	@Override
	public Tag consume(Node load) {
	if(!load.getContent().isEmpty()){
		if(load instanceof Tag){
			this.tags.add((Tag)load);
		}
	}
	return this;
	}


	@Override
	public Tag consume(Set< ? extends Node> load) {
		load.stream().forEach(tag -> consume(tag));
		return this;
		}



}
