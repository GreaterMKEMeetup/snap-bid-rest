package org.gmjm.snapbid.domain.repository;

import org.gmjm.snapbid.domain.model.Auction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AuctionRepository extends JpaRepository<Auction, Long>
{

}
