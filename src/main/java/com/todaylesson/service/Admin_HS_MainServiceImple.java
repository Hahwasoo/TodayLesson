package com.todaylesson.service;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.todaylesson.DTO.OrderListDTO;
import com.todaylesson.Mapper.Admin_HS_MainMapper;

@Service(value="admin_HS_MainService")
public class Admin_HS_MainServiceImple implements Admin_HS_MainService {

	@Resource(name="admin_HS_MainMapper")
	private Admin_HS_MainMapper adminmainMapper;
	
	//������ �г��ӹޱ�
	@Override
	public String adminMemberNick(String member_id) {
		// TODO Auto-generated method stub
		return adminmainMapper.adminMemberNick(member_id);
	}
	
	//���ϰ����ڼ� ����
	@Override
	public int memberJoinCount() {
		// TODO Auto-generated method stub
		return adminmainMapper.memberJoinCount();
	}
		
	//���� �ôϾ���ȯ�� ����
	@Override
	public int seniorChangeCount() {
		// TODO Auto-generated method stub
		return adminmainMapper.seniorChangeCount();
	}
	
	//���ϰԽñۼ� ����
	@Override
	public int freeboardWriteCount() {
		// TODO Auto-generated method stub
		return adminmainMapper.freeboardWriteCount();
	}

	//���� �Ǹűݾ� ����
	@Override
	public int orderlistCostSum() {
		// TODO Auto-generated method stub
		return adminmainMapper.orderlistCostSum();
	}

	//�������Ȳ
	  //��ϵȻ�ǰ
	   @Override
	   public int registrationProductCount() {
		   // TODO Auto-generated method stub
		   return adminmainMapper.registrationProductCount();
	   }
	  //�ǸŰ��ɻ�ǰ
	   @Override
	   public int possibilityProductCount() {
		   // TODO Auto-generated method stub
		   return adminmainMapper.possibilityProductCount();
	   }
	  //ǰ����ǰ
	   @Override
   	   public int soldOutProductCount() {
		   // TODO Auto-generated method stub
		   return adminmainMapper.soldOutProductCount();
	   }
	
	//������Ȳ
	  //��ϵ� ���� 
	   @Override
		public int registrationLessonCount() {
			// TODO Auto-generated method stub
			return adminmainMapper.registrationLessonCount();
		}

	   //���·���
		@Override
		public int openLessonCount() {
			// TODO Auto-generated method stub
			return adminmainMapper.openLessonCount();
		}
	  //��������
		@Override
		public int closeLessonCount() {
			// TODO Auto-generated method stub
			return adminmainMapper.closeLessonCount();
		}
	  //�ο���������
		@Override
		public int soldOutLessonCount() {
			// TODO Auto-generated method stub
			return adminmainMapper.soldOutLessonCount();
		}
		
	//�ֹ���Ȳ
	  //�ֹ��Ϸ�
	  @Override
	  public int orderCompleteCount() {
		// TODO Auto-generated method stub
		return adminmainMapper.orderCompleteCount();
	  }
	  //�����
	  @Override
	  public int orderDuringShippingCount() {
		// TODO Auto-generated method stub
		return adminmainMapper.orderDuringShippingCount();
	  }
	  //��ۿϷ�
	  @Override
	  public int orderShippingCompleteCount() {
		// TODO Auto-generated method stub
		return adminmainMapper.orderShippingCompleteCount();
	  }
	  //�ֹ����
	  @Override
	  public int orderCancelCount() {
		// TODO Auto-generated method stub
		return adminmainMapper.orderCancelCount();
	  }
		
	//������Ȳ
	  //�����Ϸ�
	  @Override
	  public int paymentCompleteCount() {
		// TODO Auto-generated method stub
		return adminmainMapper.paymentCompleteCount();
	  }
	  //ȯ������
	  @Override
	  public int refundAcceptCount() {
		// TODO Auto-generated method stub
		return adminmainMapper.refundAcceptCount();
	  }
	  //ȯ�ҿϷ�
	  @Override
      public int refundCompleteCount() {
		// TODO Auto-generated method stub
		return adminmainMapper.refundCompleteCount();
	  }  
		
	//����������Ȳ
	  //�����ű�����  
	  @Override
	  public int newLessonAcceptCount() {
		// TODO Auto-generated method stub
		return adminmainMapper.newLessonAcceptCount();
	  }
	  //�����ɻ���
	  @Override
	  public int newLessonEvaluationCount() {
		// TODO Auto-generated method stub
		return adminmainMapper.newLessonEvaluationCount();
	  }	
	  //��������
	  @Override
	  public int newLessonAcceptanceCount() {
		// TODO Auto-generated method stub
		return adminmainMapper.newLessonAcceptanceCount();
	  }
	  //��������
	  @Override
	  public int newLessonRefuseCount() {
		// TODO Auto-generated method stub
		return adminmainMapper.newLessonRefuseCount();
	  }
	  
	//�ôϾ�������Ȳ
	  //������
	  @Override
	  public int seniorCalculateWaitCount() {
		// TODO Auto-generated method stub
		return adminmainMapper.seniorCalculateWaitCount();
      }
	  //���갡��
	  @Override
	  public int seniorCalculatePossibleCount() {
		// TODO Auto-generated method stub
		return adminmainMapper.seniorCalculatePossibleCount();
	  }
	  //����Ϸ�
	  @Override
	  public int seniorCalculateCompleteCount() {
		// TODO Auto-generated method stub
		return adminmainMapper.seniorCalculateCompleteCount();
	  }
	  
	//1:1������Ȳ
	  //��������
	  @Override
	  public int questionLessonCount() {
		// TODO Auto-generated method stub
		return adminmainMapper.questionLessonCount();
	  }
	  //�ôϾ��
	  @Override
	  public int questionSeniorCount() {
		// TODO Auto-generated method stub
		return adminmainMapper.questionSeniorCount();
	  }
	  //������
	  @Override
	  public int questionStoreCount() {
		// TODO Auto-generated method stub
		return adminmainMapper.questionStoreCount();
	  }
	  //�ֹ�����
	  @Override
	  public int questionOrderCount() {
		// TODO Auto-generated method stub
		return adminmainMapper.questionOrderCount();
	  }
	  //��Ÿ����
	  @Override
	  public int questionOtherCount() {
		// TODO Auto-generated method stub
	 	return adminmainMapper.questionOtherCount();
	  }
	  //�亯���
	  @Override
	  public int questionAnswerWaitCount() {
		// TODO Auto-generated method stub
		return adminmainMapper.questionAnswerWaitCount();
	  }
	  //�亯�Ϸ�
	  @Override
	  public int questionAnswerCompleteCount() {
		// TODO Auto-generated method stub
		return adminmainMapper.questionAnswerCompleteCount();
	  }

	//����ī�װ�
	@Override
	public int lessonITCount() {
		// TODO Auto-generated method stub
		return adminmainMapper.lessonITCount();
	}

	@Override
	public int lessonCookCount() {
		// TODO Auto-generated method stub
		return adminmainMapper.lessonCookCount();
	}

	@Override
	public int lessonHandmadeCount() {
		// TODO Auto-generated method stub
		return adminmainMapper.lessonHandmadeCount();
	}

	@Override
	public int lessonSportCount() {
		// TODO Auto-generated method stub
		return adminmainMapper.lessonSportCount();
	}

	@Override
	public int lessonEducationCount() {
		// TODO Auto-generated method stub
		return adminmainMapper.lessonEducationCount();
	}

	@Override
	public int lessonOtherCount() {
		// TODO Auto-generated method stub
		return adminmainMapper.lessonOtherCount();
	}

	//��ǰī�װ�
	@Override
	public int productITCount() {
		// TODO Auto-generated method stub
		return adminmainMapper.productITCount();
	}

	@Override
	public int productCookCount() {
		// TODO Auto-generated method stub
		return adminmainMapper.productCookCount();
	}

	@Override
	public int productHandmadeCount() {
		// TODO Auto-generated method stub
		return adminmainMapper.productHandmadeCount();
	}

	@Override
	public int productSportCount() {
		// TODO Auto-generated method stub
		return adminmainMapper.productSportCount();
	}

	@Override
	public int productEducationCount() {
		// TODO Auto-generated method stub
		return adminmainMapper.productEducationCount();
	}

	@Override
	public int productOtherCount() {
		// TODO Auto-generated method stub
		return adminmainMapper.productOtherCount();
	}

	//���ɴ뺰 ȸ����Ȳ
	@Override
	public int memberAge10Sum() {
		// TODO Auto-generated method stub
		return adminmainMapper.memberAge10Sum();
	}

	@Override
	public int memberAge20Sum() {
		// TODO Auto-generated method stub
		return adminmainMapper.memberAge20Sum();
	}

	@Override
	public int memberAge30Sum() {
		// TODO Auto-generated method stub
		return adminmainMapper.memberAge30Sum();
	}

	@Override
	public int memberAge40Sum() {
		// TODO Auto-generated method stub
		return adminmainMapper.memberAge40Sum();
	}

	@Override
	public int memberAge50Sum() {
		// TODO Auto-generated method stub
		return adminmainMapper.memberAge50Sum();
	}

	@Override
	public int memberAge60Sum() {
		// TODO Auto-generated method stub
		return adminmainMapper.memberAge60Sum();
	}

	@Override
	public int memberAge70PlusSum() {
		// TODO Auto-generated method stub
		return adminmainMapper.memberAge70PlusSum();
	}

	//�������(�Ϻ�, �ֺ�, ����, �⺰)
	@Override
	public List<OrderListDTO> adMainStatSalesAllChart(String ymd) {
		// TODO Auto-generated method stub
		HashMap<String, Object> hm=new HashMap<>();
		hm.put("ymd", ymd);
		
		return adminmainMapper.adMainStatSalesAllChart(hm);
	}

	//AmChart
	@Override
	public List<OrderListDTO> chartOutput() {
		// TODO Auto-generated method stub
		return adminmainMapper.chartOutput();
	}
	
}
