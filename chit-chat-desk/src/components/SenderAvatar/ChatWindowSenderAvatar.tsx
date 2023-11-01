/**
 * <p>
 * 聊天窗口发送者头像
 * </p>
 *
 * @version: v1.0
 * @author: Clover
 * @create: 2023-10-30 10:12
 */
import Image from 'next/image'

export interface ChatWindowSenderAvatarProps {
  className?: string
}

export function ChatWindowSenderAvatar(props: ChatWindowSenderAvatarProps) {
  return (
    <div
      className={`h-9 w-9 border-red-500 rounded-md overflow-hidden ${props.className}`}>
      <Image
        width={'36'}
        height={'36'}
        src={
          'https://thirdqq.qlogo.cn/g?b=sdk&k=CYyF5a7VlibMMnibClx52uLw&kti=ZTYbewAAAAA&s=100&t=1688390589'
        }
        alt={''}
      />
    </div>
  )
}
