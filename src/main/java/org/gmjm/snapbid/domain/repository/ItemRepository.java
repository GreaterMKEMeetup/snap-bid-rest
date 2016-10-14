package org.gmjm.snapbid.domain.repository;

import org.gmjm.snapbid.domain.model.Auction;
import org.gmjm.snapbid.domain.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ItemRepository extends JpaRepository<Item, Long>
{

}
