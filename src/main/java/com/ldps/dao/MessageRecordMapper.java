package com.ldps.dao;

import com.ldps.model.MessageRecord;

public interface MessageRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MessageRecord record);

    int insertSelective(MessageRecord record);

    MessageRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MessageRecord record);

    int updateByPrimaryKey(MessageRecord record);
}