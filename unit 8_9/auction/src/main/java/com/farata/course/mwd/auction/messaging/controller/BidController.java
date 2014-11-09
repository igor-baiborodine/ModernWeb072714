package com.farata.course.mwd.auction.messaging.controller;

import com.farata.course.mwd.auction.dao.ProductDao;
import com.farata.course.mwd.auction.model.Bid;
import com.farata.course.mwd.auction.model.Product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import java.security.Principal;


/**
 * Serves bid-related messaging requests.
 */
@Controller
@MessageMapping("/bid")
public class BidController {

  @Autowired
  ProductDao productDao;

  @Autowired
  private SimpMessagingTemplate template;

  @MessageMapping("/place")
  //@SendToUser("/bid_reply")
  public void placeBid(@Payload Bid bid, Principal principal) {
    Product product = productDao.getById(bid.getProductId());

    if (bid.getAmount().compareTo(product.getReservedPrice()) >= 0) {
      bid.setIsWinning(true);
    }

    template.convertAndSend("/topic/bid_broadcast", bid);

    if (bid.getIsWinning()) {
      template.convertAndSendToUser(principal.getName(), "/bid_reply", bid);
    }
  }
}
