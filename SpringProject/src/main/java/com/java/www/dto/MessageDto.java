package com.java.www.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class MessageDto {
	
	private int m_no;
	private String m_sent_id;
	private String m_recv_id;
	private String m_note;
	private String m_date_sent;
	private String m_date_read;
	private int m_recv_read;
	private int m_sent_del;
	private int m_recv_del;
	
	
	
}
