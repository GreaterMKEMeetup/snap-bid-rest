package org.gmjm.snapbid.domain.repository;

import org.gmjm.snapbid.domain.model.AuctionWatch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AuctionWatchRepository  extends JpaRepository<AuctionWatch, Long>
{
	Page<AuctionWatch> findByUserId(Pageable pageable,@Param("userId") String userId);
}
