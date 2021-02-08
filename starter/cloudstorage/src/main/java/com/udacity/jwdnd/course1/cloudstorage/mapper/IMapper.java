package com.udacity.jwdnd.course1.cloudstorage.mapper;

public interface IMapper {

    int delete(Integer userid);
    int insert(Object o);
    int search(Integer userid);
    int update(Object o);

    int deleteAll();

}
