package com.CimonHe.dao;

import com.CimonHe.pojo.Comic;

import java.util.List;

public interface ComicMapper {

    List<Comic> queryAllComic ();

    int addComic (Comic comic);

    int deleteComicByComicName (String comicName);

    int updateByComicName(Comic comic);

    List<Comic> queryComicByTag(String tag);

    int countAllComic ();

    int countComicByTag (String tag);

    int countComicByUsername (String username);

    List<Comic> getAllUserComic(String username);

}
