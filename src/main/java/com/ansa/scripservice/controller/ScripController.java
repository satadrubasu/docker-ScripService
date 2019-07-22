package com.ansa.scripservice.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ansa.scripservice.entity.Scrip;
import com.ansa.scripservice.repository.ScripRepository;

@RestController
@RequestMapping({"/api"})
public class ScripController {
	
   Logger logger = LoggerFactory.getLogger(ScripController.class);

   @Autowired
   private ScripRepository repos;
  
   @PostMapping("/scrips")
   public Scrip create(@RequestBody Scrip scrip)
   {
	   logger.debug("Creating Scrip for : "+ scrip.toString());
	   return repos.save(scrip);
   }

   @GetMapping("/scrips")
   public List<Scrip> findAll()
   {
	   logger.debug("Returning all Scrips.");
	   return repos.findAll();
   }

   @DeleteMapping("/scrips/{scripId}")
   public List<Scrip> delete(@PathVariable("scripId") Long scripId)
   {
	   logger.debug("Attempting to delete Scrip for scripId: "+ scripId);
	   repos.deleteById(scripId);
       return repos.findAll();
   }



   @GetMapping("/scrips/{scripId}")
   @ResponseBody
   public Optional<Scrip> findByScripId(@PathVariable("scripId") Long scripId)
   {
	   logger.debug("Attempting to fetch Scrip for scripId: "+ scripId);
	   return repos.findById(scripId);
   }
	
}
