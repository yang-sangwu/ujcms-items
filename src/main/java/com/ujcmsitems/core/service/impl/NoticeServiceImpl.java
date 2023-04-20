package com.ujcmsitems.core.service.impl;

import com.ujcmsitems.core.Repository.NoticeRepository;
import com.ujcmsitems.core.domain.Notice;
import com.ujcmsitems.core.dto.NoticeDto;
import com.ujcmsitems.core.service.NoticeService;
import com.ujcmsitems.utils.Response;
import com.ujcmsitems.utils.YangUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author a1002
 */
@Service
public class NoticeServiceImpl implements NoticeService {
    @Resource
    private NoticeRepository noticeRepository;

    @Override
    @Transactional
    public Response addNotice(NoticeDto noticeDto) {
        String noticeTitle=noticeDto.getNoticeTitle();
        String noticeContent=noticeDto.getNoticeContent();
        String firstTarget=noticeDto.getFirstTarget();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-hh-ss");
        Date date = new Date();
        int status = 0;
        Notice notice = new Notice(noticeTitle, noticeContent, format.format(date), status, firstTarget,noticeDto.getHtmlUrl());
        noticeRepository.save(notice);
        return Response.ok("添加成功！");
    }

    @Override
    @Transactional
    public Response updateNotice(int id, String noticeTitle, String noticeContent, int status, String firstTarget,String htmlUrl) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-hh-ss");
        Date date = new Date();
        Notice notice = new Notice(id, noticeTitle, noticeContent, format.format(date), status, firstTarget,htmlUrl);
        noticeRepository.save(notice);
        return Response.ok("修改成功！");
    }

    @Override
    @Transactional
    public Response deleteNotice(int id) {
        noticeRepository.deleteById(id);
        return Response.ok("删除成功！");
    }

    @Override
    @Transactional
    public Response findNoticeByPages(int pages, int num) {
        List<Notice> list = noticeRepository.findAll();
        return Response.ok(YangUtils.queryPages(list, pages, num));
    }

    @Override
    @Transactional
    public Notice findNoticeById(int id) {
        Notice notice = noticeRepository.findById(id).orElse(null);

        return notice;
    }


    @Override
    public List<Notice> queryNoticeFour(String firstTarget) {
        return noticeRepository.queryNoticeFour(firstTarget);
    }


    @Override
    public List<Notice> queryAllNotice() {
        return noticeRepository.queryAllNotice();
    }

}
