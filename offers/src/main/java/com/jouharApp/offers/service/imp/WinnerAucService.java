package com.jouharApp.offers.service.imp;

import com.jouharApp.offers.enumeration.StatutAuction;
import com.jouharApp.offers.model.Auction;
import com.jouharApp.offers.model.WinnerAuction;
import com.jouharApp.offers.repository.AuctionRepo;
import com.jouharApp.offers.repository.WinnerAuctionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WinnerAucService {
    @Autowired
    WinnerAuctionRepo winAuctionRepository;
    @Autowired
    AuctionRepo auctionRep ;
    public WinnerAuction saveWinAuction(WinnerAuction winnerAuction) {

        return winAuctionRepository.save(winnerAuction);
    }


    public List<WinnerAuction> getWinnerAuctionBuuser(Long id) {
        return winAuctionRepository.findWinnerAuctionByIduser( id ) ;
    }
    public List<WinnerAuction> getWinnerAuctiionbyseller(Long id) {
        return winAuctionRepository.findWinnerAuctionByIdsellerAndStatus(id,StatutAuction.accepter) ;
    }
    public void accepterOffre(Long id){
        System.out.println(id);
        WinnerAuction au1=winAuctionRepository.findWinnerAuctionById( id );
        System.out.println(au1);
        au1.setStatus( StatutAuction.accepter );
        winAuctionRepository.save( au1 );
    }
    public void refuserOffre(Long id){
        WinnerAuction au1=winAuctionRepository.findWinnerAuctionById( id );
        au1.setStatus( StatutAuction.refuser );

        List<Auction> auctions = this.auctionRep.findAuctionByOffer_Id(au1.getOffer().getId());

        List<Auction> sortedUsers = auctions.stream()
                .sorted( Comparator.comparing( Auction::getPrice ).reversed() )
                .collect( Collectors.toList() );
        WinnerAuction winAuc = new WinnerAuction();
        winAuc.setOffer(au1.getOffer());
        winAuc.setPriceWin( sortedUsers.get( 1 ).getPrice() );
        winAuc.setIduser( sortedUsers.get( 1 ).getIduser() );
        winAuc.setIduser( sortedUsers.get( 1 ).getOffer().getIduser() );
        winAuc.setStatus( StatutAuction.notyet );
        this.winAuctionRepository.save( winAuc );
        winAuctionRepository.save( au1 );
    }
}
