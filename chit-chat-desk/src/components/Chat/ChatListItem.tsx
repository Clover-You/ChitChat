/**
 * <p>
 * 聊天列表项
 * </p>
 *
 * @version: v1.0
 * @author: Clover
 * @create: 2023-10-23 14:32
 */
import * as React from 'react'
import Image from 'next/image'
import dayjs from 'dayjs'

interface ChatListItemEvents {
  // 点击事件
  onClick?: () => void
}

interface ChatListItemProps extends ChatListItemEvents {
  // 选中
  selected?: boolean
  // 头像
  avatar?: string
  // 标题
  title?: string
  // 时间
  date?: string
  // 摘要
  summary?: string
  // 优化日期
  optimizedDate?: boolean
}

export const ChatListItem = React.memo(function ChatListItem(
  props: ChatListItemProps
) {
  const { optimizedDate = true } = props
  // 如果当前项被选中则背景呈现灰色
  const bgColor = props.selected ? 'bg-zinc-400/30 dark:bg-zinc-300/10 ' : ''

  const avatarUrl =
    props.avatar ||
    'https://thirdqq.qlogo.cn/g?b=sdk&k=CYyF5a7VlibMMnibClx52uLw&kti=ZTYbewAAAAA&s=100&t=1688390589'

  function MessageDateFormat() {
    const date = dayjs(props.date)
    const isToday = date.isSame(dayjs(), 'day')
    const pattern = isToday && optimizedDate ? 'HH:mm' : 'YYYY/MM/DD HH:mm'

    return dayjs(props.date).format(pattern)
  }

  return (
    <div
      className={`chat-list-item p-4 ${bgColor} flex space-x-3 `}
      onClick={props.onClick}>
      <div className="chat-list-item-avatar rounded h-11 w-11 overflow-hidden flex-shrink-0">
        <Image
          className="pointer-events-none"
          width={44}
          height={44}
          src={avatarUrl}
          alt={props.title ?? ''}
        />
      </div>

      <div className="chat-list-item-content flex overflow-hidden flex-grow flex-col justify-evenly">
        <div className="chat-list-item-content-title-box flex justify-between items-center">
          <p className="chat-list-item-content-title text-sm overflow-ellipsis overflow-hidden whitespace-nowrap">
            {props.title}
          </p>

          <div className="chat-list-item-content-time text-xs text-gray-600">
            <MessageDateFormat />
          </div>
        </div>

        <p className="chat-list-item-content-messag text-xs text-gray-400 overflow-ellipsis overflow-hidden whitespace-nowrap">
          {props.summary}
        </p>
      </div>
    </div>
  )
})
