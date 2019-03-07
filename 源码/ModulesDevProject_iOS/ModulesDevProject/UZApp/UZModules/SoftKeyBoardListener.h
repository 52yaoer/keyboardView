//
//  EaseMobData.h
//  UZApp
//
//  Created by 赵飞 on 16/8/2.
//  Copyright © 2016年 APICloud. All rights reserved.
//

#import <UIKit/UIKit.h>

@protocol SoftKeyBoardChangeDelegate <NSObject>

@optional
- (void)keyBoardShow:(int)height;
- (void)keyBoardHide:(int)height;
@end

@interface SoftKeyBoardListener : NSObject
    
@property (nonatomic, weak) id<SoftKeyBoardChangeDelegate> delegate;

+(SoftKeyBoardListener *)obtain;

-(void)setListener:(BOOL)show;
@end
