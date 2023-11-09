package com.example.firstjobapp.Review.service.impl;

import com.example.firstjobapp.Company.entity.Company;
import com.example.firstjobapp.Company.service.CompanyService;
import com.example.firstjobapp.Review.entity.Review;
import com.example.firstjobapp.Review.repository.ReviewRepository;
import com.example.firstjobapp.Review.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private CompanyService companyService;
    @Override
    public List<Review> getAllReviews (Long companyId) {
        List<Review> reviews=reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public boolean addReview (Long companyId, Review review) {
        Company company=companyService.getCompanyById (companyId);
        if(company!=null){
            review.setCompany (company);
            reviewRepository.save (review);
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public Review getReview (Long companyId, Long reviewId) {
       List<Review> reviews= reviewRepository.findByCompanyId (companyId);
       return reviews.stream ()
               .filter (review -> review.getId ()
               .equals (reviewId))
               .findFirst ()
               .orElse (null);
    }

    @Override
    public boolean updateReview (Long companyId, Long reviewId, Review updatedReview) {
        if(companyService.getCompanyById (companyId)!=null && reviewRepository.existsById (reviewId)){
            updatedReview.setCompany (companyService.getCompanyById (companyId));
            updatedReview.setId (reviewId);
            reviewRepository.save (updatedReview);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean deleteReview (Long companyId, Long reviewId) {
       if(companyService.getCompanyById (companyId)!=null&&reviewRepository.existsById (reviewId)){
           Review review=reviewRepository.findById (reviewId).orElse (null);
           Company company=review.getCompany ();
           company.getReviews ().remove (review);
           review.setCompany (null);
           companyService.updateCompany (companyId,company);
           reviewRepository.deleteById (reviewId);
           return true;

       }else {
        return false;
       }
    }

}
